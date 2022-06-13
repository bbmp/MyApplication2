package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.serialport.helper.SerialPortHelper;
import android.serialport.helper.SphResultCallback;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aidl.IMqttService;
import com.api.GitHub;
import com.bean.Contributor;
import com.example.myapplication.databinding.ActivityMainBinding;

import com.example.myapplication.http.RetrofitClient;
import com.example.myapplication.skin.SkinStatusBarUtils;
import com.example.myapplication.ui.DrawerActivity;
import com.example.myapplication.ui.PictureActivity;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.utils.ColorUtils;
import com.utils.LogUtils;
import com.utils.MsgUtils;
import com.utils.PickImageHelperTwo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // Used to load the 'myapplication' library on application startup.

    private int baudRate = 9600;
    private int dataBits = 8;
    private char checkBits = 'N';
    private int stopBits = 1;
    private String path = "dev/ttyS3";

    private String[] entryValues;
    private boolean isOpen;
    private StringBuilder receiveTxt = new StringBuilder();
    private ActivityMainBinding binding;
    private SerialPortHelper serialPortHelper;
    private BlockingQueue blockingQueue = new ArrayBlockingQueue(1);
    private Handler mainHandler;
    private String cameraID;
    private CameraDevice mCameraDevice;//摄像头
    private ImageReader mImageReader; //摄像头照片
    private CameraManager cameraManager;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;
    private IMqttService mqttService;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SkinStatusBarUtils.setStatusBarLightMode(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        View mStateBarFixer = binding.statusBarFix;
//        if (mStateBarFixer != null) {
//            ViewGroup.LayoutParams layoutParams = mStateBarFixer.getLayoutParams();
//            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            layoutParams.height = 100;
//            mStateBarFixer.setLayoutParams(layoutParams);
//            mStateBarFixer.setBackgroundColor(Color.BLACK);
//        }
        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        if (uiModeManager.getNightMode()==UiModeManager.MODE_NIGHT_YES) {
            LogUtils.e("night");
        }

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText("");

        byte[] payload = new byte[]{1, 0, 0, 0, 1, 2, 4, 3};
        Log.d(TAG, "收到命令：" + Arrays.toString(payload));

        Button send = binding.send;
        Button btn_first = binding.btnFirst;
        Button btn_second = binding.btnSecond;
        Button btn_post = binding.post;
        FrameLayout fl = binding.flLayout;
        AppCompatSpinner spinner = binding.sp1;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SerialPortHelper.getInstance().addCommands("124".getBytes(StandardCharsets.UTF_8));
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, TestActivity.class);
//                startActivity(intent);
//                getPicturePixel(getBitMapInfo(MainActivity.this));
//                DialogType0 dialogType0 = new DialogType0(MainActivity.this);
//                dialogType0.setListeners(new IRokiDialog.DialogOnClickListener() {
//                    @Override
//                    public void onClick(View v, int position) {
//
//                    }
//                }, R.id.common_dialog_ok_btn, R.id.common_dialog_cancel_btn);
//                dialogType0.setCancelable(false);
//                dialogType0.show();
//                PickImageHelperTwo.showPickDialog(MainActivity.this, PickImageHelperTwo.PHOTO_REQUEST_GALLERY , 1);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });

        String load = "7018043258438765497694576";
        byte btyee = 3;
        short aa = MsgUtils.getShort(btyee);
        short cc = (short) (btyee&0xFF);
        short bb = MsgUtils.byteToShortLittle(load.getBytes(), 1);
        double a = MsgUtils.getDouble(load.getBytes(), 2);
        double b = MsgUtils.bytes2DoubleLittle(load.getBytes(), 2);

        // 初始化串口
        SerialPortHelper serialPortHelper = SerialPortHelper.getInstance();

        // 开启串口
        serialPortHelper.openDevice(new SphResultCallback() {
            @Override
            public void onSendData(byte[] sendCom) {
                //已经在主线程
                Log.d(TAG, "发送命令：" + Arrays.toString(sendCom));
            }

            @Override
            public void onReceiveData(byte[] data) {
                Log.d(TAG, "收到命令：" + Arrays.toString(data));

            }

        });

        GitHub gitHub = RetrofitClient.getInstance().createApi(GitHub.class);
        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                Log.d(TAG, "response=" + response);
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
//                if (null == thirdFragment) {
//                    thirdFragment = new ThirdFragment();
//                    fragmentManager.add(R.id.fl_layout, thirdFragment,  "first");
//                }else
//                    fragmentManager.show(thirdFragment);
//                if (null == secondFragment) {
//                    secondFragment = new SecondFragment();
//                    fragmentManager.add(R.id.fl_layout, secondFragment,  "second");
//                } else
//                    fragmentManager.hide(secondFragment);
//                fragmentManager.commitAllowingStateLoss();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
//                fragmentManager.show(secondFragment);
//
//                fragmentManager.hide(thirdFragment);
//                fragmentManager.commitAllowingStateLoss();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Intent serviceStartIntent = new Intent();
        serviceStartIntent.setClassName(this, "com.example.myapplication.mqtt.MqttService");
        bindService(serviceStartIntent, serviceConnection,
                Context.BIND_AUTO_CREATE);

        List<String> data = new ArrayList<String>();
        data.add("春天");
        data.add("夏天");
        data.add("秋天");
        data.add("冬天");

        //建立Adapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  //使用系统预先定义的布局
//       		R.layout.myitem, //对Spinner文本框使用自定义的布局
                data);
        spinner.setAdapter(adapter);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file
                        = new File(getCacheDir() + File.separator + "distkitch/index.html");
                RequestBody body
                        = MultipartBody
                        .create(MultipartBody.FORM, file);
                MultipartBody.Part part
                        = MultipartBody.Part
                        .createFormData("file", file.getName(), body);
//http://192.168.1.100/MyUploadServer/servlet/UpLoadFileServlet
                Call<ResponseBody> call = gitHub.postUpLoadFile(part);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.e("onResponse", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure", t.getMessage());
                    }
                });
            }
        });
    }

    private final class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mqttService = IMqttService.Stub.asInterface(binder);

            // now that we have the service available, we can actually
            // connect...
            Log.e("thread", " name=" + Thread.currentThread().getId());
            try {
                mqttService.connect();
                Log.e("thread2", " name=" + Thread.currentThread().getId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mqttService = null;
        }
    }

    // Listener for when the service is connected or disconnected
    private final MyServiceConnection serviceConnection = new MyServiceConnection();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortHelper.getInstance().closeDevice();
    }

    private void initCamera2() {
        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();

        mainHandler = new Handler(Looper.getMainLooper());
        cameraID = "" + CameraCharacteristics.LENS_FACING_EXTERNAL;
        mImageReader = ImageReader.newInstance(540, 399, ImageFormat.JPEG, 1);
        //变形处理
        RectF previewRect = new RectF(0, 0, 540, 399);
        RectF surfaceDimensions = new RectF(0, 0, 540, 960);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(previewRect, surfaceDimensions, Matrix.ScaleToFit.FILL);


        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                try {
                    // 拿到拍照照片数据
                    Image image = reader.acquireNextImage();
//                    Image image = reader.acquireLatestImage();
                    if (image != null) {
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);//由缓冲区存入字节数组

                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

//                        File file = new File(Const.PHOTO_PATH);
//                        file.createNewFile();
//                        FileOutputStream os = new FileOutputStream(file);
//                        BufferedOutputStream bos = new BufferedOutputStream(os);
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
//                        bos.flush();
//                        bos.close();
//                        os.close();
                        image.close();//如果频繁使用拍照，必须调用此方法要不然会报错
                        //   java.lang.IllegalStateException: maxImages (1) has already been acquired, call #close before acqu
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, mainHandler);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            cameraManager.openCamera(cameraID, stateCallback, mainHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            //开启预览
//            takePreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Toast.makeText(MainActivity.this, "摄像头开启失败", Toast.LENGTH_SHORT).show();
        }
    };

    private void requestPermission() {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                        if (aBoolean) {
                            //全部已经授权
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, WebActivity.class);
                            startActivity(intent);

                        } else {
                            //起码有一个没有授权

                        }

                    }
                });
    }

    public void getPicturePixel(Bitmap bitmap) {

        ColorUtils.caculate(bitmap);


    }

    private Bitmap getBitMapInfo(Context mContext) {
        Bitmap bitmap = null;
        try {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels>displayMetrics.heightPixels?displayMetrics.widthPixels:displayMetrics.heightPixels;
            int height = displayMetrics.widthPixels>displayMetrics.heightPixels?displayMetrics.heightPixels:displayMetrics.widthPixels;
            float scale;

            InputStream is = mContext.getAssets().open("dangao.jpg");
            Bitmap mSrcBitmap = BitmapFactory.decodeStream(is);
            is.close();
            int bitWidth = mSrcBitmap.getWidth();
            int bitHeight = mSrcBitmap.getHeight();

            float sw = width*1.0f/bitWidth;
            float sh = height*1.0f/bitHeight;
            scale = sw<sh?sw:sh;

            //压缩
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            bitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PickImageHelperTwo.PHOTO_REQUEST_GALLERY == requestCode) {
            if (null != data) {
                PickImageHelperTwo.setImageUri(data.getData());
                Intent intent = new Intent();

                intent.setClass(this, PictureActivity.class);
                startActivity(intent);
            }

        } else if (PickImageHelperTwo.PHOTO_REQUEST_CAMERA == requestCode) {

            Intent intent = new Intent();
            intent.setClass(this, PictureActivity.class);
            startActivity(intent);

        }
    }
}