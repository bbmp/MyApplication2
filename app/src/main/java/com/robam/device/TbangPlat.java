package com.robam.device;


import android.content.Context;

import com.topband.tbapi.TBManager;


public class TbangPlat implements IPlat {
    private TBManager tbManager;

    public TbangPlat(Context context) {
        tbManager = new TBManager(context);
        tbManager.init();
    }

    @Override
    public void init(Context context) {

    }

    /**
     * 获取设备型号
     * @return
     */
    @Override
    public String getDt() {
        return "DB620";
    }
    /**
     * 获取设备唯一标识 先取mac地址
     * @return
     */
    @Override
    public String getDeviceOnlySign() {
        return getDt() + getMac().replace(":" , "");
    }

    @Override
    public String getMac() {
//        return tbManager.getWiFiMac().replace(":" , "");
        return "121212121212";
    }

    @Override
    public void openDoorLamp() {
        tbManager.setGpioDirection(6, 1, 0);
        tbManager.setGpio(6, 1);
    }

    @Override
    public void closeDoorLamp() {
        tbManager.setGpioDirection(6, 1, 0);
        tbManager.setGpio(6, 0);
    }

    @Override
    public void openWaterLamp() {
        tbManager.setGpioDirection(6, 1, 0);
        tbManager.setGpio(6, 1);
    }

    @Override
    public void closeWaterLamp() {
        tbManager.setGpioDirection(6, 1, 0);
        tbManager.setGpio(6, 0);
    }

    @Override
    public void openPowerLamp() {
        tbManager.setGpioDirection(5, 1, 0);
        tbManager.setGpio(5, 1);
    }

    @Override
    public void closePowerLamp() {
        tbManager.setGpioDirection(5, 1, 0);
        tbManager.setGpio(5, 0);
    }



    @Override
    public void closeNavBar() {
        tbManager.setNavBar(false);
    }

    @Override
    public void closeStatusBar() {
        tbManager.setStatusBar(false);
    }

    /**
     * 关闭屏幕
     */
    @Override
    public void screenOff() {
        tbManager.screenOff();
        //关灯
        closePowerLamp();
        closeWaterLamp();
    }

    /**
     * 开启屏幕
     */
    @Override
    public void screenOn() {
        tbManager.screenOn();
        //开灯
        openPowerLamp();
    }
}

