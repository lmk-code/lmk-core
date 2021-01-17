package com.lmk.core.commons.utils;

import java.security.SecureRandom;

/**
 * 随机数生成器
 * @author LaoMake
 * @since 1.0
 *
 */
public class RandomUtils {

    /** 随机数发生器 */
    private static SecureRandom random = new SecureRandom();

    /**
     * 获取一个随机整数
     * @return
     */
    public static int nextInt(){
        return random.nextInt();
    }

    /**
     * 获取一个随机整数
     * @param bound 边界
     * @return
     */
    public static int nextInt(int bound){
        return random.nextInt(bound);
    }

    /**
     * 产生随机的字节数组
     * @param bytes
     */
    public static void nextBytes(byte[] bytes){
        random.nextBytes(bytes);
    }

}
