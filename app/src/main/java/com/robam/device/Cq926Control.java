package com.robam.device;

import com.example.myapplication.MainActivity;
import com.example.myapplication.bean.SettingMultiModeBean;
import com.example.myapplication.bean.SteamOven;
import com.example.myapplication.constant.SteamOvenStatusEnum;
import com.example.myapplication.manager.ActivityManager;
import com.utils.DateUtil;

public class Cq926Control {

    public void shutDown() {
        ActivityManager.getInstance().finishAllActivities(MainActivity.class);
        byte[] payload = SerialPortMsgHelper.powerOff();
        SteamOven.getInstance().marshaller(payload);
        DeviceFactory.getPlatform().screenOff();
    }

    public void powerOn() {
        byte[] payload = SerialPortMsgHelper.powerOn();
        SteamOven.getInstance().marshaller(payload);
        DeviceFactory.getPlatform().screenOn();
    }

    public void orderWork() {
        byte[] payload = SerialPortMsgHelper.orderWork();
        SteamOven.getInstance().marshaller(payload);
    }

    public void stopWork() {

    }

    public void startWork() {
        byte[] payload = SerialPortMsgHelper.stopWork();
        SteamOven.getInstance().marshaller(payload);
    }

    public static void pauseWork() {
        SteamOvenStatusEnum statusEnum = SteamOvenStatusEnum.match(SteamOven.getInstance().workState);
        byte[] payload = null;
        if (SteamOvenStatusEnum.WORK_STATE_PREHEAT == statusEnum) {
            payload = SerialPortMsgHelper.pauseWork((short) SteamOvenStatusEnum.WORK_STATE_PREHEAT_PAUSE.getCode());
        } else if (SteamOvenStatusEnum.WORK_STATE_WORK == statusEnum) {
            payload = SerialPortMsgHelper.pauseWork((short) SteamOvenStatusEnum.WORK_STATE_WORK_PAUSE.getCode());
        }
        SteamOven.getInstance().marshaller(payload);
    }

    public static void continueWork() {
        SteamOvenStatusEnum statusEnum = SteamOvenStatusEnum.match(SteamOven.getInstance().workState);
        byte[] payload = null;
        if (SteamOvenStatusEnum.WORK_STATE_PREHEAT_PAUSE == statusEnum) {
            payload = SerialPortMsgHelper.pauseWork((short) SteamOvenStatusEnum.WORK_STATE_PREHEAT.getCode());
        } else if (SteamOvenStatusEnum.WORK_STATE_WORK_PAUSE == statusEnum) {
            payload = SerialPortMsgHelper.pauseWork((short) SteamOvenStatusEnum.WORK_STATE_WORK.getCode());
        }
        SteamOven.getInstance().marshaller(payload);
    }

    public static void onMqttControl() {
        //工作判断
        if (SteamOven.getInstance().workCtrl == 0) {

        } else if (SteamOven.getInstance().workCtrl == 1) {
            //只有一段 工作
            if (SteamOven.getInstance().multiMode.size() < 2) {
                //先清除本地多段
                SteamOven.getInstance().removeAllMultiMode();
                //已经预约中 直接开始 参数已经缓存过
                if (SteamOven.getInstance().orderTime != 0) {
//                    if (skipAction != null) {
//                        skipAction.toStartWork();
//                    }
                    return;
                }
//                if (skipAction != null) {
//                    skipAction.toStartWork();
//                }
            } else {
                //先清除本地多段
                SteamOven.getInstance().removeAllMultiMode();
                SettingMultiModeBean settingMultiModeBean = new SettingMultiModeBean();
                settingMultiModeBean.mode = SteamOven.getInstance().mode;
                settingMultiModeBean.setTemp = SteamOven.getInstance().setUpTemp;
                settingMultiModeBean.setDownTemp = SteamOven.getInstance().setDownTemp;
                settingMultiModeBean.setTime = SteamOven.getInstance().setTime;
                SteamOven.getInstance().addMultiMode(settingMultiModeBean);
                SettingMultiModeBean settingMultiModeBean2 = new SettingMultiModeBean();
                settingMultiModeBean2.mode = SteamOven.getInstance().mode2;
                settingMultiModeBean2.setTemp = SteamOven.getInstance().setUpTemp2;
                settingMultiModeBean2.setDownTemp = SteamOven.getInstance().setDownTemp2;
                settingMultiModeBean2.setTime = SteamOven.getInstance().setTime2;
                SteamOven.getInstance().addMultiMode(settingMultiModeBean2);
                SettingMultiModeBean settingMultiModeBean3 = new SettingMultiModeBean();
                if (SteamOven.getInstance().mode3 != 0){
                    settingMultiModeBean3.mode = SteamOven.getInstance().mode3;
                    settingMultiModeBean3.setTemp = SteamOven.getInstance().setUpTemp3;
                    settingMultiModeBean3.setDownTemp = SteamOven.getInstance().setDownTemp3;
                    settingMultiModeBean3.setTime = SteamOven.getInstance().setTime3;
                    SteamOven.getInstance().addMultiMode(settingMultiModeBean3);
                }
                //多段
//                if (skipAction != null) {
//                    skipAction.toStartMultWork();
//                }
            }
        } else if (SteamOven.getInstance().workCtrl == 2) {
            //暂停工作
//            if (skipAction != null) {
//                skipAction.toPasueWork();
//            }
            pauseWork();
        } else if (SteamOven.getInstance().workCtrl == 3) {
            //预约
//            if (SteamOven.getInstance().setOrderSecs != 0) {
//                SteamOven.getInstance().mode = mode;
//                SteamOven.getInstance().setUpTemp = setUpTemp;
//                SteamOven.getInstance().setTempDown = setDownTemp;
//                SteamOven.getInstance().setTime = setTime;
//                //设置剩余时间 未开始工作同首段时间
//                SteamOven.getInstance().restTime = setTime;
//                String formatedDateTime = DateUtil.getFormatedDateTime(DateUtil.PATTERN, (DateUtil.currentTimestampLong() + setOrderSecs * 1000));
//                SteamOven.getInstance().orderDate = formatedDateTime;
//                orderWork();
////                if (skipAction != null) {
////                    skipAction.toOrderWork();
////                }
//            }
        } else if (SteamOven.getInstance().workCtrl == 4) {
            //继续工作
            continueWork();
        }
    }
}
