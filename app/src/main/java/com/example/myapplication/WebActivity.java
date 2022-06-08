package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.myapplication.Jsbridge.JavaInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tvJs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webview);
        tvJs = findViewById(R.id.tv_js);

        initSetting();
        copyFileOrDir("distkitch");
        String url = getCacheDir() + File.separator + "distkitch/index.html";
        String url2 = "http://develop.h5.myroki.com/distkitch/index.html";
        webView.loadUrl(url);
        tvJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String payload = "device is on";
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    webView.evaluateJavascript("javascript:testAndroidUseJsFun('"+payload+"')", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //处理结果
                        }
                    });
                } else {
                    webView.loadUrl("javascript:testAndroidUseJsFun('"+payload+"')");
                }
            }
        });
    }

    private void initSetting() {
        WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        //身份认证Cookie丢失问题
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        webSettings.setDatabaseEnabled(true);

        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

//优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//缓存模式如下：
//LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
//LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
//LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
//不使用缓存

        webView.addJavascriptInterface(new JavaInterface(this), "control");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void copyFileOrDir(String path) {

        AssetManager assetManager = getAssets();

        String assets[] = null;

        try {

            assets = assetManager.list(path);

//复制单个文件

            if (assets.length == 0)

            {

                copyFile(path);

            }

//复制文件夹中的文件到另一个目录中

            else

            {

                for (int i = 0; i < assets.length; ++i)

                {

                    Log.e("Path",path + "/" + assets[i]);
                    String dir = getCacheDir() + "/" + path;
                    if (!new File(dir).exists())
                        new File(dir).mkdir();

                    copyFileOrDir(path + "/" + assets[i]);

                }

            }

        } catch (IOException ex) {

            Log.e("tag", "I/O Exception", ex);

        }

    }

    private void copyFile(String filename) {

        AssetManager assetManager = getAssets();

        InputStream in = null;

        OutputStream out = null;

        try {

            in = assetManager.open(filename);

            String newFileName = getCacheDir() + File.separator + filename;

            if (!new File(newFileName).exists())
                new File(newFileName).createNewFile();
            Log.e("here",newFileName);

            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];

            int read;

            while ((read = in.read(buffer)) != -1) {

                out.write(buffer, 0, read);

            }

            in.close();

            in = null;

            out.flush();

            out.close();

            out = null;

        } catch (Exception e) {

            Log.e("copyFile", e.getMessage());

        }

    }
}