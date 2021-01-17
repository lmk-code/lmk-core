package com.lmk.core.cloud.support.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import com.lmk.core.web.support.utils.HttpUtils;

/**
 * Feign请求拦截器，需要手动导入到IOC容器
 * 用于拦截Feign客户端的请求，我们需要手动恢复请求头信息
 */
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从当前线程获取请求对象
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        // 在Feign客户端，发起新的请求之前，设置请求头信息
        requestTemplate.header("Content-Type", request.getHeader("Content-Type"));
        requestTemplate.header("Authorization", request.getHeader("Authorization"));
    }

}
