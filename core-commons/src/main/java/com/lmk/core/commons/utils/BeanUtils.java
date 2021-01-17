package com.lmk.core.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean工具类
 */
public class BeanUtils {

    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 获取父类型的第一个泛型
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Class<T> getSuperClassFirstGenerics(Class<?> clazz){
        Class<T> type = null;
        // 获取父类的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取实体类的类型
        // 获取泛型数组
        Type[] types = parameterizedType.getActualTypeArguments();
        if(types != null && types.length > 0){
            type = (Class<T>) types[0];
        }
        return type;
    }

    /**
     * 根据注解类型获取属性值
     * @param obj
     * @param annotationClass
     * @return
     */
    public static Object findFieldValueByAnnotation(Object obj, Class annotationClass){
        Object value = null;
        Class<?> clazz = obj.getClass();
        Field field = findFieldByAnnotation(clazz, annotationClass);
        if(field != null){
            try {
                field.setAccessible(true);
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("获取属性值失败失败：{}, {}", clazz, annotationClass);
        }
        return value;
    }

    /**
     * 按注解类型搜索属性字段
     * @param clazz
     * @param annotationClass
     * @return
     */
    public static Field findFieldByAnnotation(Class<?> clazz, Class annotationClass){
        Field field = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field fieldItem : fields){
            if(fieldItem.getAnnotation(annotationClass) != null){
                field = fieldItem;
                break;
            }
        }
        return field;
    }
}
