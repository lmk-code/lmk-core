package com.lmk.core.web.support.api;

/**
 * 请求响应的状态码
 * @author 编程浪子
 * @email laomake@hotmail.com
 */
public class StatusCode{

    /** 代码 */
    protected int code;

    /** 信息 */
    protected String text;

    public StatusCode() {
    }

    public StatusCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
