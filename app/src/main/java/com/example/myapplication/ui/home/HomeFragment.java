package com.example.myapplication.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.bean.SteamOven;
import com.example.myapplication.bean.SteamOvenViewModel;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.robam.device.Cq926Control;
import com.utils.LogUtils;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SteamOvenViewModel steamOvenViewModel =
                new ViewModelProvider(this).get(SteamOvenViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView title = binding.tvTitle;
        CardView cardView = binding.llLayout;

        steamOvenViewModel.getSteamOven().observe(getViewLifecycleOwner(), new Observer<SteamOven>() {
            @Override
            public void onChanged(SteamOven steamOven) {
                LogUtils.e("数据更新");
                title.setText("update" + steamOven.beep_type);
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] payload = new byte[] {1, 2, 3, 4, 1, 32, 1, 2, 43, 5, 1, 3, 4, 5, 6, 8, 4, 12, 3, 4, 5, 5, 6, 2, 1, 5, 8, 43, 1};
                steamOvenViewModel.getSteamOven().unmarshaller(payload);
            }
        });
        SteamOven.getInstance().onUpdateState();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}