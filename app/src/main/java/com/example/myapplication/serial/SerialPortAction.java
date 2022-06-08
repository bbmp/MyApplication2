package com.example.myapplication.serial;

public interface SerialPortAction {

    void marshaller(byte[] payload);

    /**
     * 反序列化串口返回数据
     * @param payload
     */
    void unmarshaller(byte[] payload);
}

