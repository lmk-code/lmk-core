package com.lmk.core.web.auth.bean;

/**
 * 应用令牌
 * @author LaoMake
 * @since 1.0
 */
public interface AppToken {

    /**
     * 获取令牌
     * @return
     */
    String getToken();

    /**
     * 获取应用ID
     * @return
     */
    default String getAppId(){
        return null;
    }

    /**
     * 获取应用密钥
     * @return
     */
    default String getAppSecret(){
        return null;
    }

}
