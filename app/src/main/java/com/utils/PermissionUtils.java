package com.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import com.example.myapplication.MainActivity;
import com.example.myapplication.WebActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

public class PermissionUtils {

    public static void requestPermission(Activity activity, OnPermissionListener listener, String... permissions) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(
                permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {

                        if (aBoolean) {
                            //全部已经授权
                            if (null != listener)
                                listener.onSucceed();

                        } else {
                            //起码有一个没有授权
                            if (null != listener)
                                listener.onFailed();
                        }

                    }
                });
    }

    public interface OnPermissionListener {
        void onSucceed();

        void onFailed();
    }
}
