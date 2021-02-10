package com.lmk.core.web.monitor.bean;

/**
 * 日志类型：
 * 0.公共类型，1.前台操作，2.后台操作，3.系统自动
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public enum LogType {

    // 公共类型
    COMMON(0),

    // 前台操作
    FRONT(1),

    // 后台操作
    BACK(2),

    // 系统自动
    SYS(3);

    private int value;

    LogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
