package com.lmk.core.cloud.support.helper;

import feign.FeignException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import com.lmk.core.web.support.utils.HttpUtils;
import com.lmk.core.commons.utils.JsonUtils;
import com.lmk.core.web.support.api.RequestResult;
import com.lmk.core.web.support.api.GenericStatusCode;

/**
 * HTTP请求辅助工具类
 * 主要功能：处理RestTemplate、Feign请求
 * @author LaoMake
 * @since 1.0
 */
public class HttpHelper {

    /**
     * 封装HttpEntity，可以设置Http请求头和请求参数
     * @param request
     * @param entity
     * @return
     */
    public static <T> HttpEntity<T> getHttpEntity(HttpServletRequest request, T entity){
        // 从原始的HttpServletRequest对象中获取请求头
        // 然后重新添加、封装到httpEntity对象中
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", request.getHeader("Content-Type"));
        headers.add("Authorization", request.getHeader("Authorization"));

        HttpEntity<T> httpEntity = new HttpEntity<>(entity, headers);
        return httpEntity;
    }

    /**
     * 处理RestTemplate请求异常，从异常对象中创建返回值
     * @param e
     * @param response
     * @return
     */
    public RequestResult getRequestResult(HttpClientErrorException e, HttpServletResponse response){
        // 请求失败，我们自己再次封装错误信息
        // 1. 提取、转接服务提供者返回的状态码
        response.setStatus(e.getRawStatusCode());
        // 2. 提取、转接服务提供者返回的错误信息
        return JsonUtils.parseObject(e.getResponseBodyAsString(), RequestResult.class);
    }

    /**
     * 解析熔断异常，设置返回值
     * @param throwable
     * @return
     */
    public static RequestResult parseHystrixException(Throwable throwable){
        RequestResult requestResult = null;
        // 如果是Feign调用出错，代表服务提供者是可用的，这是一个业务异常
        // 此时我们应该原模原样的返回服务提供者的错误信息
        if(throwable instanceof FeignException){
            FeignException e = (FeignException) throwable;
            HttpUtils.getHttpServletResponse().setStatus(e.status());
            requestResult = JsonUtils.parseObject(e.contentUTF8(), RequestResult.class);
        }else{
            // 否则，代表服务提供者不可用，则直接返回指定的错误信息
            requestResult = new RequestResult(GenericStatusCode.Busy);
        }
        return requestResult;
    }
}
