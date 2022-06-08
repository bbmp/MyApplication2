package com.example.myapplication.mqtt;

import android.os.RemoteException;
import android.util.Log;

import com.aidl.IMqttService;


public class MqttServiceBinder extends IMqttService.Stub {


    @Override
    public void connect() throws RemoteException {
        Log.e("thread", " name=" + Thread.currentThread().getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
