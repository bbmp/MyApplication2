package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogType2;
import com.example.myapplication.skin.SkinStatusBarUtils;
import com.example.myapplication.ui.view.MaskView;
import com.utils.ColorUtils;
import com.utils.LogUtils;
import com.utils.PickImageHelperTwo;
import com.utils.ScreenUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PictureActivity extends AppCompatActivity {
    private MaskView maskView;
    private Button btRound, btSquare, btCaculate;
    private Button btRadd, btRdec;
    private Group group;
    private TextView tvTotal;
    private static TextView tvNum;
    private static TextView tvMin, tvMax, tvAvg;
    private Button btOK;
    private static double max, min, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        View mStateBarFixer = findViewById(R.id.status_bar_fix);
        View mStateBarFixer2 = findViewById(R.id.status_bar_fix2);
        if (mStateBarFixer != null) {
            ViewGroup.LayoutParams layoutParams = mStateBarFixer.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = SkinStatusBarUtils.getStatusbarHeight(this);
            mStateBarFixer.setLayoutParams(layoutParams);
        }
        if (mStateBarFixer2 != null) {
            ViewGroup.LayoutParams layoutParams = mStateBarFixer2.getLayoutParams();
            layoutParams.width = SkinStatusBarUtils.getStatusbarHeight(this);
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mStateBarFixer2.setLayoutParams(layoutParams);
        }
        maskView = findViewById(R.id.iv_image);
        btRound = findViewById(R.id.add_round);
        btSquare = findViewById(R.id.add_square);
        btCaculate = findViewById(R.id.caculate);
        btRadd = findViewById(R.id.tv_radd);
        btRdec = findViewById(R.id.tv_rdec);

        group = findViewById(R.id.group);
        tvTotal = findViewById(R.id.tv_total);
        tvNum = findViewById(R.id.tv_num);
        tvMin = findViewById(R.id.tv_min);
        tvMax = findViewById(R.id.tv_max);
        tvAvg = findViewById(R.id.tv_avg);
        btOK = findViewById(R.id.common_dialog_ok_btn);
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setVisibility(View.GONE);
            }
        });
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
        btRadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addR();
            }
        });
        btRadd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                maskView.addR();
                return false;
            }
        });
        btRdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.decR();
            }
        });
        btRdec.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                maskView.decR();
                return false;
            }
        });
        btRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addRound();
            }
        });
        btSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addSquare();
            }
        });
        btCaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setVisibility(View.VISIBLE);
                total = 0;
                max = 0;
                min = Integer.MAX_VALUE;

                tvTotal.setText("总块数   " + maskView.getRoundNum());
                final int[] num = {0};
                maskView.spiltRound(new ColorUtils.CaculateCallback() {
                    @Override
                    public void onSuccess(double score, double bgpercent) {
                        num[0]++;
                        Message msg = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putInt("num", num[0]);
                        bundle.putDouble("score", score);
                        bundle.putDouble("bgpercent", bgpercent);
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onFailed() {

                    }
                });
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
    static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int num = bundle.getInt("num");
            double score = bundle.getDouble("score");
            double bgpercent = bundle.getDouble("bgpercent");
            tvNum.setText("已完成   " + num);
            total = total + score;
            if (max < score)
                max = score;
            if (min > score)
                min = score;
            tvMax.setText("最大值   " + max);
            tvMin.setText("最小值   " + min);
            tvAvg.setText("平均值   " + total/num);
        }
    };
}