package com.example.myapplication.mqtt;

import java.util.List;

public interface IProtocol {

    byte[] encode(MqttMsg msg) throws Exception;

    int decode(String topic, byte[] data) throws Exception;
}
