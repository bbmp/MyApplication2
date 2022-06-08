//package com.serial.jni;
//
//import android.app.ActivityManager;
//import android.util.Log;
//
//import java.security.SecureRandom;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class SerialPortHelper {
//
//    private final static String TAG = SerialPortHelper.class.getSimpleName();
//
//    private boolean mIsOpen = false;
//    private int baudRate = 9600;
//    private int dataBits = 8;
//    private char checkBits = 'N';
//    private int stopBits = 1;
//    private String path = "dev/ttyS3";
//
//    private SerialPortConfig serialPortConfig;
//
//    private SphThreads sphThreads;
//
//    private static SerialPortHelper INSTANCE;
//    /**
//     * 最大接收数据的长度
//     */
//    private int maxSize;
//
//    /**
//     * 是否需要返回最大数据接收长度
//     */
//    private boolean isReceiveMaxSize;
//
//    /**
//     * 数据处理
//     */
//    private SphDataProcess processingData;
//
//    private ExecutorService executorService = new ThreadPoolExecutor(2, 2,
//            0L, TimeUnit.MILLISECONDS,
//            new ArrayBlockingQueue<>(5));
//    private byte[] payload = new byte[]{1, 0, 0, 0, 1, 2, 4,3};
//
//    public static SerialPortHelper getInstance() {
//        if (null == INSTANCE) {
//            synchronized (SerialPortHelper.class) {
//                if (null == INSTANCE)
//                    INSTANCE = new SerialPortHelper();
//            }
//        }
//        return INSTANCE;
//    }
//    private SerialPortHelper() {
//        serialPortConfig = new SerialPortConfig();
//        serialPortConfig.path = path;
//        serialPortConfig.baudRate = baudRate;
//        serialPortConfig.dataBits = dataBits;
//        serialPortConfig.parity = checkBits;
//        serialPortConfig.stopBits = stopBits;
//
//        // 设置默认串口参数
//        setMaxSize(16);
//        setReceiveMaxSize(false);
//    }
//
//    /**
//     * 串口设置
//     */
//    public void setMaxSize(int maxSize) {
//        this.maxSize = maxSize;
//    }
//
//    public void setReceiveMaxSize(Boolean isReceiveMaxSize) {
//        this.isReceiveMaxSize = isReceiveMaxSize;
//    }
//
//    /**
//     * 打开串口设备
//     */
//    public void openDevice(SphResultCallback onResultCallback){
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                if(serialPortConfig==null){
//                    throw new IllegalArgumentException("'SerialPortConfig' must can not be null!!! ");
//                }
//                if(serialPortConfig.path == null){
//                    throw new IllegalArgumentException("You not have setting the device path, " +
//                            "you must 'new SerialPortHelper(String path)' or call 'openDevice(String path)' ");
//                }
//
//                int i = SerialPortJNI.openPort(
//                        serialPortConfig.path,
//                        serialPortConfig.baudRate,
//                        serialPortConfig.dataBits,
//                        serialPortConfig.stopBits,
//                        serialPortConfig.parity);
//
//                // 打开串口成功
//                if(i==1){
//                    mIsOpen = true;
//                    // 创建数据处理
//                    processingData = new SphDataProcess(maxSize);
//                    processingData.setRecevieMaxSize(isReceiveMaxSize);
//                    processingData.setSphResultCallback(onResultCallback);
//                    // 开启读写线程
//                    sphThreads = new SphThreads(processingData);
//
//                    //循环
//                    while (true) {
//                        processingData.addCommands(payload);
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }else{
//                    mIsOpen = false;
//                    Log.e(TAG,"cannot open the device !!! " +
//                            "path:"+serialPortConfig.path);
//                }
//            }
//        });
//    }
//
//    /**
//     * 发送串口命令
//     * @param commands 串口命令
//     *
//     */
//    public void addCommands(byte[] commands){
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                if(!isOpenDevice()){
//                    Log.e(TAG,"You not open device !!! ");
//                    return;
//                }
//                // 添加发送命令
//                processingData.addCommands(commands);
//            }
//        });
//    }
//
//
//    /**
//     * 关闭串口
//     */
//    public void closeDevice(){
//        SerialPortJNI.closePort();
//        if(sphThreads !=null){
//            sphThreads.stop();
//        }
//    }
//
//    /**
//     * 判断串口是否打开
//     */
//    public boolean isOpenDevice(){
//        return mIsOpen;
//    }
//
//
//
//}
