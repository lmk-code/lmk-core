package com.lmk.core.web.support.bean.http;

import com.lmk.core.web.support.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户端信息
 * @author LaoMake
 * @since 1.0
 */
public class ClientInfo {

    /** 请求地址 */
    private String url;

    /** 请求方式 */
    private String method;

    /** 客户端IP */
    private String ip;

    public ClientInfo() {
    }

    public ClientInfo(HttpServletRequest request) {
        this.url = request.getServletPath();
        this.method = request.getMethod();
        this.ip = HttpUtils.getClientIp(request);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
