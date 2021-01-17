package com.lmk.core.cloud.support.helper;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import com.lmk.core.web.support.api.RequestResult;
import com.lmk.core.commons.utils.JsonUtils;

/**
 * REST 服务请求工具
 * @author LaoMake
 * @since 1.0
 *
 */
public class RestHelper {

    /** 日志记录器 */
    private static Logger logger = LoggerFactory.getLogger(RestHelper.class);

    /**
     * 从请求失败的异常对象中，创建返回值
     * @param e
     * @param response
     * @return
     */
    private static RequestResult getRequestResult(HttpClientErrorException e, HttpServletResponse response){
        // 请求失败，我们自己再次封装错误信息
        // 1. 提取、转接服务提供者返回的状态码
        response.setStatus(e.getRawStatusCode());
        // 2. 提取、转接服务提供者返回的错误信息
        return JsonUtils.parseObject(e.getResponseBodyAsString(), RequestResult.class);
    }

    /**
     * 获取唯一对象
     * @param url
     * @param type
     * @param restTemplate
     * @param <T>
     * @return
     */
    public static <T> T getForObject(String url, Class<T> type, RestTemplate restTemplate){
        return getForObject(url, type, restTemplate, null);
    }

    /**
     *
     * @param url
     * @param type
     * @param restTemplate
     * @param headers
     * @param <T>
     * @return
     */
    public static <T> T getForObject(String url, Class<T> type, RestTemplate restTemplate, HttpHeaders headers){
        RequestResult requestResult = null;

        // 空值校验
        if(headers == null)
            headers = new HttpHeaders();

        try {
            ResponseEntity<RequestResult> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    RequestResult.class
            );
            requestResult = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("服务请求失败：{}", url);
            e.printStackTrace();
        }

        // 构建返回值
        T entity = null;
        if(requestResult != null){
            String json = JsonUtils.toJSON(requestResult.getData().get("bean"));
            entity = JsonUtils.parseObject(json, type);
        }
        return entity;
    }

    /**
     * 获取请求结果的集合
     * @param url
     * @param restTemplate
     * @return
     */
    public static Map<String, Object> getForMap(String url, RestTemplate restTemplate){
        return getForMap(url, restTemplate, null);
    }


    /**
     * 获取请求结果的集合
     * @param url
     * @param restTemplate
     * @param headers
     * @return
     */
    public static Map<String, Object> getForMap(String url, RestTemplate restTemplate, HttpHeaders headers){
        RequestResult requestResult = null;

        // 空值校验
        if(headers == null)
            headers = new HttpHeaders();

        try {
            ResponseEntity<RequestResult> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    RequestResult.class
            );
            requestResult = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("服务请求失败：{}", url);
            e.printStackTrace();
        }

        // 构建返回值
        Map<String, Object> result = null;
        if(requestResult != null){
            result = (Map<String, Object>) requestResult.getData().get("bean");
        }
        return result;
    }

    /**
     * 获取对象列表
     * @param url
     * @param type
     * @param restTemplate
     * @param <T>
     * @return
     */
    public static <T> List<T> getForList(String url, Class<T> type, RestTemplate restTemplate){
        return getForList(url, type, restTemplate, null);
    }

    /**
     * 获取对象列表
     * @param url
     * @param type
     * @param restTemplate
     * @param headers
     * @param <T>
     * @return
     */
    public static <T> List<T> getForList(String url, Class<T> type, RestTemplate restTemplate, HttpHeaders headers){
        RequestResult requestResult = null;

        // 空值校验
        if(headers == null)
            headers = new HttpHeaders();

        try {
            ResponseEntity<RequestResult> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    RequestResult.class
            );
            requestResult = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("服务请求失败：{}", url);
            e.printStackTrace();
        }

        // 构建返回值
        List<T> entityList = null;
        if(requestResult != null){
            String json = JsonUtils.toJSON(requestResult.getData().get("bean"));
            entityList = JsonUtils.parseList(json, type);
        }
        return entityList;
    }

    /**
     * 获取对象集合列表
     * @param url
     * @param restTemplate
     * @return
     */
    public static List<Map<String, Object>> getForMapList(String url, RestTemplate restTemplate){
        return getForMapList(url, restTemplate, null);
    }

    /**
     * 获取对象集合列表
     * @param url
     * @param restTemplate
     * @param headers
     * @return
     */
    public static List<Map<String, Object>> getForMapList(String url, RestTemplate restTemplate, HttpHeaders headers){
        RequestResult requestResult = null;

        // 空值校验
        if(headers == null)
            headers = new HttpHeaders();

        try {
            ResponseEntity<RequestResult> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    RequestResult.class
            );
            requestResult = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("服务请求失败：{}", url);
            e.printStackTrace();
        }

        // 构建返回值
        List<Map<String, Object>> mapList = null;
        if(requestResult != null){
            mapList = (List<Map<String, Object>>) requestResult.getData().get("list");
        }
        return mapList;
    }
}