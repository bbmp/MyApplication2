//package com.serial.jni;
//
//public class SerialPortJNI {
//    static {
//        System.loadLibrary("myapplication");
//    }
//    /**
//     * 打开串口并设置串口数据位，校验位, 速率，停止位
//     * @param path        串口地址
//     * @param baudRate    波特率
//     * @param dataBits    数据位
//     * @param stopBits    停止位
//     * @param parity      校验类型 取值N ,E, O
//     * @return
//     */
//    public static native int openPort(String path,int baudRate, int dataBits,
//                                      int stopBits,char parity);
//
//    /**
//     * 读取串口数据
//     * @param maxSize  数据最大长度
//     * @return 串口数据
//     */
//    public static native byte[] readPort(int maxSize);
//
//    /**
//     * 写入串口数据
//     * @param datas   串口数据指令
//     */
//    public static native void writePort(byte[] datas);
//
//    /**
//     * 关闭串口
//     */
//    public static native void closePort();
//
//
////    private static native int run(int cmdLen, String[] cmd);
//}
