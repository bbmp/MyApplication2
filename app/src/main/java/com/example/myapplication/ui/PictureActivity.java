package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;

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
import com.example.myapplication.ui.view.LongClickButton;
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
    private Button btRound, btSquare;
    //圆半径
    private LongClickButton btRadd, btRdec;
    //矩形长
    private LongClickButton btWadd, btWdec;
    //矩形高
    private LongClickButton btHadd, btHdec;
    //圆
    private Button caculateRound;
    //矩形
    private Button caculateSquare;

    private Group group, group2, group3;
    private TextView tvTotal;
    private static TextView tvNum;
    private static TextView tvMin, tvMax, tvAvg, tvBg;
    private Button btOK;
    private static double max, min, total;
    //所有像素和背景像素
    private static int totalPixels, bgPixels;
    private static boolean isComplete = true;
    //结果显示辅助线
    private Guideline guideline3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        View mStateBarFixer = findViewById(R.id.status_bar_fix);
        if (mStateBarFixer != null) {
            ViewGroup.LayoutParams layoutParams = mStateBarFixer.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = SkinStatusBarUtils.getStatusbarHeight(this);
            mStateBarFixer.setLayoutParams(layoutParams);
        }

        maskView = findViewById(R.id.iv_image);
        btRound = findViewById(R.id.add_round);
        btSquare = findViewById(R.id.add_square);
        caculateRound = findViewById(R.id.caculate_round);
        caculateSquare = findViewById(R.id.caculate_square);
        btRadd = findViewById(R.id.tv_radd);
        btRdec = findViewById(R.id.tv_rdec);
        btWadd = findViewById(R.id.tv_widthadd);
        btWdec = findViewById(R.id.tv_widthdec);
        btHadd = findViewById(R.id.tv_heightadd);
        btHdec = findViewById(R.id.tv_heightdec);
        guideline3 = findViewById(R.id.guideline3);

        group = findViewById(R.id.group);
        group2 = findViewById(R.id.group2);
        group3 = findViewById(R.id.group3);
        tvTotal = findViewById(R.id.tv_total);
        tvNum = findViewById(R.id.tv_num);
        tvMin = findViewById(R.id.tv_min);
        tvMax = findViewById(R.id.tv_max);
        tvAvg = findViewById(R.id.tv_avg);
        tvBg = findViewById(R.id.tv_bg);
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
                int width = ScreenUtils.getWidthPixels(this) + SkinStatusBarUtils.getStatusbarHeight(this);// 加上状态栏高度
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

                //缩放到图片完整显示，宽或高充满屏幕
                float sw = width*1.0f/srcWidth;
                float sh = height*1.0f/srcHeight;
                scale = sw<sh?sw:sh;
                matrix.setScale(scale, scale);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, srcWidth, srcHeight, matrix, true);

                maskView.setBitmap(bitmap);
                guideline3.setGuidelineBegin((int) (scale * srcWidth));


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
        btRadd.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.addR();
            }
        });
        btRdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.decR();
            }
        });
        btRdec.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.decR();
            }
        });
        btRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addRound();
                group2.setVisibility(View.VISIBLE);
                btSquare.setVisibility(View.GONE);
            }
        });
        btSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addSquare();
                group3.setVisibility(View.VISIBLE);
                btRound.setVisibility(View.GONE);
            }
        });
        caculateRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isComplete)
                    return;
                isComplete = false;
                group.setVisibility(View.VISIBLE);
                total = 0;
                max = 0;
                min = Integer.MAX_VALUE;
                totalPixels = 0;
                bgPixels = 0;

                tvTotal.setText("总块数   " + maskView.getRoundNum());
                final int[] num = {0};
                maskView.spiltRound(new ColorUtils.CaculateCallback() {
                    @Override
                    public void onSuccess(double score, int totalpixels, int bgpixels) {
                        synchronized (num) {
                            num[0]++;
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putInt("num", num[0]);
                            bundle.putDouble("score", score);
                            bundle.putInt("totalpixels", totalpixels);
                            bundle.putInt("bgpixels", bgpixels);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                            if (num[0] == maskView.getRoundNum())
                                isComplete = true;
                        }
                    }

                    @Override
                    public void onFailed() {

                    }
                });

            }
        });
        btWadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addWidth();
            }
        });
        btWadd.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.addWidth();
            }
        });
        btWdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.decWidth();
            }
        });
        btWdec.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.decWidth();
            }
        });
        btHadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.addHeight();
            }
        });
        btHadd.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.addHeight();
            }
        });
        btHdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maskView.decHeight();
            }
        });
        btHdec.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                maskView.decHeight();
            }
        });
        caculateSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isComplete)
                    return;
                isComplete = false;
                group.setVisibility(View.VISIBLE);
                total = 0;
                max = 0;
                min = Integer.MAX_VALUE;
                totalPixels = 0;
                bgPixels = 0;

                tvTotal.setText("总块数   " + maskView.getSquareNum());
                final int[] num = {0};
                maskView.splitSquare(new ColorUtils.CaculateCallback() {
                    @Override
                    public void onSuccess(double score, int totalpixels, int bgpixels) {
                        synchronized (num) {
                            num[0]++;
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putInt("num", num[0]);
                            bundle.putDouble("score", score);
                            bundle.putInt("totalpixels", totalpixels);
                            bundle.putInt("bgpixels", bgpixels);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                            if (num[0] == maskView.getSquareNum())
                                isComplete = true;
                        }
                    }

                    @Override
                    public void onFailed() {

                    }
                });
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
            int totalpixels = bundle.getInt("totalpixels");
            int bgpixels = bundle.getInt("bgpixels");
            tvNum.setText("已完成   " + num);
            total = total + score;
            totalPixels = totalPixels + totalpixels;
            bgPixels = bgPixels + bgpixels;
            if (max < score)
                max = score;
            if (min > score)
                min = score;
            tvMax.setText("最大值   " + max);
            tvMin.setText("最小值   " + min);
            tvAvg.setText("平均值   " + total/num);
            tvBg.setText("背景色占比:" + bgPixels*1.0f/totalPixels);
        }
    };
}