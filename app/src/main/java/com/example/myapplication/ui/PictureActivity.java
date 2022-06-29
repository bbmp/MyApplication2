package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.skin.SkinStatusBarUtils;
import com.example.myapplication.ui.view.MaskView;
import com.utils.ColorUtils;
import com.utils.PickImageHelperTwo;
import com.utils.ScreenUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PictureActivity extends AppCompatActivity {
    private MaskView maskView;
    private Button btRound, btCaculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        maskView = findViewById(R.id.iv_image);
        btRound = findViewById(R.id.add_round);
        btCaculate = findViewById(R.id.caculate);
        SkinStatusBarUtils.translucent(this);

        Uri uri = PickImageHelperTwo.getImageUri();
        if (null != uri) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                //屏幕宽度
                int width = ScreenUtils.getWidthPixels(this);
                int height = ScreenUtils.getHeightPixels(this);
                float scale;

                Matrix matrix = new Matrix();

                int srcWidth = bitmap.getWidth();
                int srcHeight = bitmap.getHeight();
                if (srcWidth < srcHeight) {
                    //旋转
                    matrix.setRotate(-90);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, srcWidth, srcHeight, matrix, true);
                    //重新获取
                    srcWidth = bitmap.getWidth();
                    srcHeight = bitmap.getHeight();
                }

                //缩放
                float sw = width*1.0f/srcWidth;
                float sh = height*1.0f/srcHeight;
                scale = sw<sh?sw:sh;
                matrix.setScale(scale, scale);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, srcWidth, srcHeight, matrix, true);

                maskView.setBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        }
        btRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addRound();
            }
        });
        btCaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.spiltRound();
//                ColorUtils.caculate(bitmap, new ColorUtils.CaculateCallback() {
//                    @Override
//                    public void onSuccess(double score) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(PictureActivity.this, "Score= "+ score, Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailed() {
//
//                    }
//                });
            }
        });
    }
}