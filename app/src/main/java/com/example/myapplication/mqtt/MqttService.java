package com.example.myapplication.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MqttService extends Service {
    private MqttServiceBinder mqttServiceBinder = new MqttServiceBinder();
    public MqttService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mqttServiceBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void connect() {
        Log.e("thread", " name=" + Thread.currentThread().getId());
    }
}