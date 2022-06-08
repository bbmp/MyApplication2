//package com.serial.jni;
//
//public class SphThreads {
//    /**
//     * 读写线程
//     */
//    private Thread readThread, writeThread;
//    /**
//     * 数据处理
//     */
//    private SphDataProcess processingData;
//
//    public SphThreads(SphDataProcess processingData) {
//        this.processingData = processingData;
//        readThread = new Thread(new ReadThread());
//        readThread.start();
//
//        writeThread = new Thread(new WriteThread());
//        writeThread.start();
//    }
//
//    /**
//     * 数据读取线程
//     */
//    public class ReadThread implements Runnable {
//
//        @Override
//        public void run() {
//            while (!readThread.isInterrupted()) {
//                // 创建数据接收数组
//                processingData.createReadBuff();
//                // 读取数据
//                try {
//                    byte[] bytes = SerialPortJNI.readPort(processingData.getMaxSize());
//                    if (bytes != null) {
//                        int revLength = bytes.length;
//                        if (revLength > 0) {
//                            processingData.processingRecData(bytes, revLength);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 数据写入线程
//     */
//    public class WriteThread implements Runnable {
//        @Override
//        public void run() {
//            while (!writeThread.isInterrupted()) {
//                //正常指令
//                processingData.writeData();
//            }
//        }
//    }
//
//    /**
//     * 停止线程
//     */
//    public void stop() {
//        if (readThread != null) {
//            readThread.interrupt();
//        }
//        if (writeThread != null) {
//            writeThread.interrupt();
//        }
//    }
//}
