package com.lmk.core.web.auth.bean;

/**
 * 登录会员
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public interface LoginMember {

    /**
     * 获取用户OpenId
     * @return
     */
    String getOpenId();

    /**
     * 获取用户名
     * @return
     */
    String getUsername();

    /**
     * 获取手机号
     * @return
     */
    String getMobile();

    /**
     * 获取昵称
     * @return
     */
    default String getNickname() {
        return null;
    }

    /**
     * 获取头像
     * @return
     */
    default String getAvatar(){
        return null;
    }

}
