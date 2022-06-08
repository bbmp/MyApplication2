package com.robam.device;

/**
 * 设备功能控制
 */
public interface IDeviceControl {
    /**
     * 关机
     */
    void shutDown() ;

    /**
     * 开机
     */
    void powerOn();

    /**
     * 预约
     */
    void orderWork();
    /**
     * 结束工作
     */
    void stopWork();

    /**
     * 启动工作
     */
    void startWork();
    /**
     * 暂停工作
     */
    void pauseWork();
    /**
     * 继续工作
     */
    void continueWork();
}
