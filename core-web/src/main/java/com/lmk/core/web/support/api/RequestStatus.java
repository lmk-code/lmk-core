package com.lmk.core.web.support.api;

/**
 * 用于客户端显示的状态信息
 * @author LaoMake
 * @since 1.0
 */
public class RequestStatus {
    private Integer code;
    private String message;

    public RequestStatus() {
    }

    public RequestStatus(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getText();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
