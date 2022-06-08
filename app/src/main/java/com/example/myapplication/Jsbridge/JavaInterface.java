package com.example.myapplication.Jsbridge;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaInterface {
    private Context mContext;

    public JavaInterface(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void deviceOn(String payload) {
        Toast.makeText(mContext, payload, Toast.LENGTH_SHORT).show();
    }
}
