package com.example.myapplication.http;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient client;
    private Retrofit retrofit;
    private String ecsHost = "118.178.157.97";
    private int ecsPort;
    private final static int CONNECT_TIMEOUT = 20;
    private final static int WRITE_TIMEOUT = 20;
    private static String defaultHost = "https://api.github.com";

    private static  RetrofitClient instance = new RetrofitClient();

    public static RetrofitClient getInstance() { return instance; }

    private RetrofitClient() {
        this(defaultHost, null);
    }

    public RetrofitClient(String url, Map<String, String> headers) {
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLogInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }

    public <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
