package com.robam.device;

import android.content.Context;

import com.example.myapplication.mqtt.IProtocol;
import com.example.myapplication.mqtt.mqttinstan.MqttSteamOven;

public class DeviceFactory {
    public final static String TUOBANG = "tuobang" ;
    public final static String CQ926 = "DB620" ;
    //平台
    private static IPlat platform ;
    //mqtt协议
    private static IProtocol protocol ;


    public static void initPlat(Context context, String plat) {
        if (TUOBANG.equals(plat))
            platform = new TbangPlat(context);
    }

    public static void initMqttProtocol(String dt) {
        if (CQ926.equals(dt))
            protocol = new MqttSteamOven();
    }

    public static IPlat getPlatform() {
        return platform;
    }

    public static IProtocol getProtocol() {
        return protocol;
    }
}
