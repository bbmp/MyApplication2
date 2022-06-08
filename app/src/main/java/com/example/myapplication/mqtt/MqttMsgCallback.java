package com.example.myapplication.mqtt;

public interface MqttMsgCallback {
    void publishSuccess();

    void messageArrived(byte[] payload);
}
