package com.lmk.core.web.auth.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录用户
 * @author LaoMake
 * @since 1.0
 */
public interface LoginUser {

    /**
     * 获取用户ID
     * @return
     */
    Integer getId();

    /**
     * 获取用户名
     * @return
     */
    String getUsername();

    /**
     * 获取用户角色列表
     * @return
     */
    default List<String> roles(){
        return new ArrayList<>();
    }

    /**
     * 获取用户权限列表
     * @return
     */
    default List<String> permissions(){
        return new ArrayList<>();
    }
}
