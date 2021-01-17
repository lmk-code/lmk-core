package com.lmk.core.web.support.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Web请求结果
 * @author LaoMake
 * @since 1.0
 */
public class RequestResult {

    /** 用于服务端判断的错误代码 */
    @JsonIgnore
    private StatusCode code;

    /** 用于客户端判断的错误代码 */
    private RequestStatus status;

    /** 返回数据 */
    private Map<String, Object> data;

    public RequestResult() {
        super();
        this.code = GenericStatusCode.Success;
        this.status = new RequestStatus(this.code);
        data = new HashMap<>();
    }

    public RequestResult(StatusCode statusCode) {
        super();
        this.code = statusCode;
        this.status = new RequestStatus(this.code);
        // 错误代码无需添加数据
        if(statusCode == GenericStatusCode.Success){
            data = new HashMap<>();
        }
    }

    /**
     * 返回请求成功结果
     * @return
     */
    public static RequestResult ok(){
        return new RequestResult();
    }

    /**
     * 返回服务端错误结果
     * @return
     */
    public static RequestResult error(){
        return new RequestResult(GenericStatusCode.ServerError);
    }

    /**
     * 返回服务端繁忙结果
     * @return
     */
    public static RequestResult busy(){
        return new RequestResult(GenericStatusCode.Busy);
    }

    /**
     * 返回请求参数错误结果
     * @return
     */
    public static RequestResult parameterError(){
        return new RequestResult(GenericStatusCode.ParameterError);
    }

    /**
     * 返回没有登录的结果
     * @return
     */
    public static RequestResult noLogin(){
        return new RequestResult(GenericStatusCode.NoLogin);
    }

    /**
     * 返回没有权限的结果
     * @return
     */
    public static RequestResult NoPermission(){
        return new RequestResult(GenericStatusCode.NoPermission);
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
        this.status = new RequestStatus(this.code);
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 完整添加一个Map中的所有数据
     * @param map
     */
    public void appendAll(Map<String, Object> map){
        this.data.putAll(map);
    }

    /**
     * 追加单个数据
     * @param key
     * @param value
     */
    public void appendData(String key, Object value){
        this.data.put(key, value);
    }

    /**
     * 移除单个数据
     * @param key
     */
    public void removeData(String key){
        this.data.remove(key);
    }

    /**
     * 清除所有数据
     */
    public void clearData(){
        this.data.clear();
    }
}
