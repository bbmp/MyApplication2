package com.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogType1;
import com.example.myapplication.dialog.IRokiDialog;

public class PickImageHelperTwo {

    public final static String CAMERA_TEXT = "相机";
    public final static String ALBUM_TEXT = "相册";
    private static String PHOTO_FILE_NAME = "output_image.jpg";
    public static final int PHOTO_REQUEST_CAMERA_CROP = 100;
    public static final int PHOTO_REQUEST_CAMERA = 101;
    public static final int PHOTO_REQUEST_GALLERY = 102;
    public static final int PHOTO_REQUEST_GALLERY_CROP = 103;
    private static final int PHOTO_REQUEST_CUT = 104;


    String strCamera = CAMERA_TEXT;
    String strAlbum = ALBUM_TEXT;
    private static Uri cropImageUri;

    private static File cameFile;
    private static Uri imageUri;

    /**
     * 允许选择最大的图片张数
     */
    private int maxSelect;

    public static final int CODE_RECIPE_DETAIL_SHARE = 1;
    public static final int CODE_HOME_DEVICE_CAMERA = 2;
    public static final int CODE_THEME_DETAIL_SHARE = 3;
    public static final int CODE_USER_INFO_SHARE = 4;
    public static final int CODE_RECIPE_DETAIL_CAMERA = 5;
    public static final int CODE_DEVICE_WATER_SHARE = 6;
    public static final int CODE_KITCHEN_SHARE_ACTIVE = 7;
    public static final int CODE_KITCHEN_SHARE_VIDEO = 8;
    public static final int CODE_WIFI_SSID = 9;
    public static final int CODE_BLUE_TOOTH = 10;




    public static void showPickDialog(Activity act, int code, int maxSelect) {

        maxSelect = maxSelect;

        List<String> data = new ArrayList<>();
        data.add(CAMERA_TEXT);
        data.add(ALBUM_TEXT);
        DialogType1 dialogType1 = new DialogType1(act);
        dialogType1.setListeners(new IRokiDialog.DialogOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                if (v.getId() == R.id.rv_menu_list && position == 0) {

                    camera(act, PHOTO_REQUEST_CAMERA);

                } else if (v.getId() == R.id.rv_menu_list && position == 1) {

                    gallery(act, PHOTO_REQUEST_GALLERY);
                }
            }
        }, R.id.card_cancel);

        dialogType1.setContentList(data);
        dialogType1.setCancelable(false);
        dialogType1.show();

    }


    public static void camera(Activity act, int code) {
        PermissionUtils.requestPermission(act, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onSucceed() {
                cameFile = new File(act.getExternalCacheDir(), System.currentTimeMillis() + "" + PHOTO_FILE_NAME);
                try {
                    if (!cameFile.exists()) {
                        cameFile.createNewFile();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
//                    第一个参数Context，第二个参数随便写个字符串，第三个参数就是我们刚刚创建的File对象

                    imageUri = FileProvider.getUriForFile(act, act.getPackageName() + ".FileProvider", cameFile);
                } else {
                    imageUri = Uri.fromFile(cameFile);
                }
                //启动相机程序

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");// 设置action
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//指定图片的输出地址
                act.startActivityForResult(intent, code);
            }

            @Override
            public void onFailed() {

            }
        }, Manifest.permission.CAMERA);

    }

    /*
     * 从相册获取
     */
    public static void gallery(Activity act, int code) {
        PermissionUtils.requestPermission(act, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onSucceed() {
// 激活系统图库，选择一张图片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY

                act.startActivityForResult(intent, code);
            }

            @Override
            public void onFailed() {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    /*
     * 剪切图片
     */
    private static void crop(Activity act, Uri uri) {
        File CropPhoto = new File(act.getExternalCacheDir(), "crop_image.jpg");
        try {
            if (CropPhoto.exists()) {
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cropImageUri = Uri.fromFile(CropPhoto);
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.putExtra("crop", "true");
        // 裁剪框的比例，4：3
        intent.putExtra("aspectX", 100);
        intent.putExtra("aspectY", 101);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("circleCrop", "false");
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        act.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static void setImageUri(Uri data) {

        // 从剪切图片返回的数据
        imageUri = data;
    }

    public static Uri getImageUri() {
        return imageUri;
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private static void getGalleryFilePath(Activity act, Intent data) {

        // 从剪切图片返回的数据
        if (data != null) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(act.getContentResolver()
                        .openInputStream(cropImageUri));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
