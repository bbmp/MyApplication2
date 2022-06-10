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
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.skin.SkinStatusBarUtils;
import com.utils.ColorUtils;
import com.utils.PickImageHelperTwo;
import com.utils.ScreenUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PictureActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        imageView = findViewById(R.id.iv_image);
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

                imageView.setImageBitmap(bitmap);

                ColorUtils.caculate(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        }
    }
}