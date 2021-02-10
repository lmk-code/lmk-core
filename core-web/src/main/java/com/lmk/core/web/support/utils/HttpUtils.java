package com.lmk.core.web.support.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP工具类
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class HttpUtils {

    /**
     * 获取访问令牌
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * 获取请求头信息
     * @param request
     * @param name
     * @return
     */
    public static String getHeader(HttpServletRequest request, String name) {
        return request.getHeader(name);
    }

    /**
     * 获取请求头信息
     * @param request
     * @param name
     * @return
     */
    public static String getHeader(ServerHttpRequest request, String name) {
        return request.getHeaders().getFirst(name);
    }

    /**
     * 从当前线程中获取 HttpServletRequest 对象
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从当前线程中获取 HttpServletResponse 对象
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        try {
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
