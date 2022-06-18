package com.example.myapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDrawerBinding;
import com.example.myapplication.databinding.AppBarDrawerBinding;
import com.example.myapplication.skin.SkinDisplayUtils;
import com.example.myapplication.skin.SkinStatusBarUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.utils.ScreenUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class DrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;
    private DrawerLayout drawer;

//    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setSupportActionBar(binding.appBarDrawer.toolbar);
        ImageView test = binding.tvTest;
        ImageView testright = binding.tvTestRight;
        binding.appBarDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                open();
            }
        });
        ImageView imageView = binding.imageView;
        TextView tvTitle = binding.tvTitle;
        TextView textView = binding.textView;

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(Gravity.LEFT);
            }
        });
        testright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(Gravity.RIGHT);
            }
        });
        drawer = binding.drawerLayout;

//        navigationView = binding.navView;

//        Toolbar toolbar = binding.appBarDrawer.toolbar;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions options = new NavOptions.Builder()
                        .setPopUpTo(navController.getCurrentDestination().getId(), true)
                        .setLaunchSingleTop(true)
                        .build();
                //跳转
                navController.navigate(R.id.navigation_dashboard, null , options);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions options = new NavOptions.Builder()
                        .setPopUpTo(navController.getCurrentDestination().getId(), true)
                        .setLaunchSingleTop(true)
                        .build();
                navController.navigate(R.id.navigation_notifications, null, options);
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ConstraintLayout mContentLayout = (ConstraintLayout) findViewById(R.id.app_bar_drawer);
//        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //抽屉滑动式回调的方法
                Log.e("tag", "x" + drawerView.getWidth());
                Log.e("tag","---onDrawerSlide---"+slideOffset);
                if (drawerView.getId() == R.id.drawer_left)
                    test.setX(slideOffset * drawerView.getWidth());
                else {
                    int width = ScreenUtils.getWidthPixels(DrawerActivity.this);
                    testright.setX(width - slideOffset * drawerView.getWidth() - SkinDisplayUtils.dp2px(DrawerActivity.this, 35));
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //抽屉打开时会回调的方法
                Log.e("tag","---onDrawerOpened---");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("tag","---onDrawerClosed---");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //抽屉的状态改变时会回调的方法
                Log.e("tag","---onDrawerStateChanged---");
            }
        });

    }

    public void open(int gravity) {
        //判断当前
        if (drawer.isDrawerOpen(gravity))
            drawer.closeDrawer(gravity);
        else {
            drawer.openDrawer(gravity);
        }
    }

}