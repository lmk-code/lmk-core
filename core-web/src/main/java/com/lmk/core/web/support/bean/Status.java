package com.lmk.core.web.support.bean;

/**
 * 通用的实体状态标识
 * @author LaoMake
 * @since 1.0
 */
public enum Status {
    ENABLE(1, "启用"),
    DISABLE(0, "不可用"),
    REMOVE(-1, "删除");

    private int code;
    private String text;

    Status(int code, String text) {
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
