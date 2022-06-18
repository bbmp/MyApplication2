package com.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ColorUtils {
    private static int colors[] = {};
    public static ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            1, 1, 0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    //色卡值
    private static int red4 = 0xFD;
    private static int green4 = 0xE7;
    private static int blue4 = 0xB0;

    private static int red17 = 0x36;
    private static int green17 = 0x33;
    private static int blue17 = 0x2D;

    private static int red16 = 0x60;
    private static int green16 = 0x3B;
    private static int blue16 = 0x25;

    private static int red15 = 0x76;
    private static int green15 = 0x43;
    private static int blue15 = 0x24;

    private static int red14 = 0x8F;
    private static int green14 = 0x55;
    private static int blue14 = 0x2A;

    private static int red13 = 0xA4;
    private static int green13 = 0x66;
    private static int blue13 = 0x28;

    private static int red12 = 0xA6;
    private static int green12 = 0x70;
    private static int blue12 = 0x3D;

    private static int red11 = 0xAA;
    private static int green11 = 0x7A;
    private static int blue11 = 0x4E;

    private static int red10 = 0xBE;
    private static int green10 = 0x84;
    private static int blue10 = 0x4C;

    private static int red9 = 0xD7;
    private static int green9 = 0x94;
    private static int blue9 = 0x2E;

    private static int red8 = 0xDD;
    private static int green8 = 0xA6;
    private static int blue8 = 0x5D;

    private static int red7 = 0xF8;
    private static int green7 = 0xB7;
    private static int blue7 = 0x5B;

    private static int red6 = 0xF7;
    private static int green6 = 0xC1;
    private static int blue6 = 0x6E;

    private static int red5 = 0xFF;
    private static int green5 = 0xDE;
    private static int blue5 = 0x94;

    private static int redbg = 0xFF;
    private static int greenbg = 0xFF;
    private static int bluebg = 0xFF;

    //同步对象
    private static Object object = new Object();

    public static void caculate(Bitmap bitmap, CaculateCallback callback) {
        new Thread() {
            @Override
            public void run() {
                final int[] totalPixels = new int[1];
                final double[] totalScore = new double[1];
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();

                LogUtils.e("width=" + width);
                LogUtils.e("height=" + height);
                // 保存所有的像素的数组，图⽚宽×⾼
                int[] pixels = new int[width * height];
                bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

                for (int i = 0; i < pixels.length; i++) {
                    int clr = pixels[i];
                    int red = (clr & 0x00ff0000) >> 16; // 取⾼两位
                    int green = (clr & 0x0000ff00) >> 8; // 取中两位
                    int blue = clr & 0x000000ff; // 取低两位


                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            int min = Integer.MAX_VALUE;
                            int score = 0;
                            //计算靠近哪个色卡值最近
                            int result = ColorUtils.getColor4(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 4;
                            }
                            result = ColorUtils.getColor5(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 5;
                            }
                            result = ColorUtils.getColor6(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 6;
                            }
                            result = ColorUtils.getColor7(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 7;
                            }
                            result = ColorUtils.getColor8(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 8;
                            }
                            result = ColorUtils.getColor9(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 9;
                            }
                            result = ColorUtils.getColor10( red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 10;
                            }
                            result = ColorUtils.getColor11( red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 11;
                            }
                            result = ColorUtils.getColor12(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 12;
                            }
                            result = ColorUtils.getColor13(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 13;
                            }
                            result = ColorUtils.getColor14(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 14;
                            }
                            result = ColorUtils.getColor15( red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 15;
                            }
                            result = ColorUtils.getColor16(red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 16;
                            }
                            result = ColorUtils.getColor17( red, green, blue);
                            if (result < min) {
                                min = result;
                                score = 17;
                            }
                            //背景色不计入
                            result = ColorUtils.getColorBG(red, green, blue);
                            if (result < min) {
                                min = result;
                                return;
                            }
                            synchronized (object) {
                                totalScore[0] = totalScore[0] + score;
                                totalPixels[0]++;
//                                    LogUtils.e("score=" + totalPixels[0]);
                            }
                        }
                    });

                }
                //判断线程池结束
                while (true) {
                    if (executorService.getTaskCount() == executorService.getCompletedTaskCount())
                        break;
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //计算平均分
                LogUtils.e("totalScore=" + totalScore[0]);
                LogUtils.e("totalPixels=" + totalPixels[0]);
                LogUtils.e("score=" + totalScore[0]/totalPixels[0]);
                if (null != callback)
                    callback.onSuccess(totalScore[0]/totalPixels[0]);
            }
        }.start();
    }
    public static int getColorBG(int red, int green, int blue) {
        int distance = ((red-redbg)*(red-redbg) + (green-greenbg)*(green-greenbg) + (blue-bluebg)*(blue-bluebg));
//        LogUtils.e("getColorBG=" + distance);
        return distance;
    }

    public static int getColor17(int red, int green, int blue) {

        int distance = ((red-red17)*(red-red17) + (green-green17)*(green-green17) + (blue-blue17)*(blue-blue17));
//        LogUtils.e("distance17=" + distance);
        return distance;
    }

    public static int getColor4(int red, int green, int blue) {
        int distance = ((red-red4)*(red-red4) + (green-green4)*(green-green4) + (blue-blue4)*(blue-blue4));
//        LogUtils.e("distance4=" + distance);
        return distance;
    }

    public static int getColor5(int red, int green, int blue) {

        int distance = ((red-red5)*(red-red5) + (green-green5)*(green-green5) + (blue-blue5)*(blue-blue5));
//        LogUtils.e("distance5=" + distance);
        return distance;
    }

    public static int getColor6(int red, int green, int blue) {

        int distance = ((red-red6)*(red-red6) + (green-green6)*(green-green6) + (blue-blue6)*(blue-blue6));
//        LogUtils.e("distance6=" + distance);
        return distance;
    }

    public static int getColor7(int red, int green, int blue) {

        int distance = ((red-red7)*(red-red7) + (green-green7)*(green-green7) + (blue-blue7)*(blue-blue7));
//        LogUtils.e("distance7=" + distance);
        return distance;
    }

    public static int getColor8(int red, int green, int blue) {

        int distance = ((red-red8)*(red-red8) + (green-green8)*(green-green8) + (blue-blue8)*(blue-blue8));
//        LogUtils.e("distance8=" + distance);
        return distance;
    }

    public static int getColor9(int red, int green, int blue) {

        int distance = ((red-red9)*(red-red9) + (green-green9)*(green-green9) + (blue-blue9)*(blue-blue9));
//        LogUtils.e("distance9=" + distance);
        return distance;
    }

    public static int getColor10(int red, int green, int blue) {

        int distance = ((red-red10)*(red-red10) + (green-green10)*(green-green10) + (blue-blue10)*(blue-blue10));
//        LogUtils.e("distance10=" + distance);
        return distance;
    }

    public static int getColor11(int red, int green, int blue) {

        int distance = ((red-red11)*(red-red11) + (green-green11)*(green-green11) + (blue-blue11)*(blue-blue11));
//        LogUtils.e("distance11=" + distance);
        return distance;
    }

    public static int getColor12(int red, int green, int blue) {

        int distance = ((red-red12)*(red-red12) + (green-green12)*(green-green12) + (blue-blue12)*(blue-blue12));
//        LogUtils.e("distance12=" + distance);
        return distance;
    }

    public static int getColor13(int red, int green, int blue) {

        int distance = ((red-red13)*(red-red13) + (green-green13)*(green-green13) + (blue-blue13)*(blue-blue13));
//        LogUtils.e("distance13=" + distance);
        return distance;
    }

    public static int getColor14(int red, int green, int blue) {

        int distance = ((red-red14)*(red-red14) + (green-green14)*(green-green14) + (blue-blue14)*(blue-blue14));
//        LogUtils.e("distance14=" + distance);
        return distance;
    }

    public static int getColor15(int red, int green, int blue) {

        int distance = ((red-red15)*(red-red15) + (green-green15)*(green-green15) + (blue-blue15)*(blue-blue15));
//        LogUtils.e("distance15=" + distance);
        return distance;
    }

    public static int getColor16(int red, int green, int blue) {

        int distance = ((red-red16)*(red-red16) + (green-green16)*(green-green16) + (blue-blue16)*(blue-blue16));
//        LogUtils.e("distance16=" + distance);
        return distance;
    }

    public interface CaculateCallback {
        void onSuccess(double score);
        void onFailed();
    }
}
