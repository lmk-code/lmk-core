package com.lmk.core.commons.utils;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON工具类
 * @author LaoMake
 * @since 1.0
 */
public class JsonUtils {
    /** 通用的解析器 */
    public static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 获取JSON字符串
     * @param obj
     * @return
     */
    public static String toJSON(Object obj){
        String json = null;
        try {
            json = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 解析对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> type){
        Object obj = null;
        try {
            obj = MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /**
     * 解析列表
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> type) {
        JavaType listType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, type);
        Object obj = null;
        try {
            obj = MAPPER.readValue(json, listType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return (List<T>) obj;
    }
}
