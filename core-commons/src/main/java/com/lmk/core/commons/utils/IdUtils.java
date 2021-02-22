package com.lmk.core.commons.utils;

import java.util.UUID;

/**
 * ID生成工具类
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class IdUtils {

    /**
     * 获得一个随机生成的UUID
     * @return 去除横线后的字符串
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得一个随机生成的UUID
     * @return 包含横线的字符串
     */
    public static String uuidFull(){
        return UUID.randomUUID().toString();
    }

}
