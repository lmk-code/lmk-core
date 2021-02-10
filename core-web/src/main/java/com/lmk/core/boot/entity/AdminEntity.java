package com.lmk.core.boot.entity;

import com.lmk.core.web.auth.bean.LoginUser;

/**
 * 管理后台实体
 * @author 编程浪子
 * @email laomake@hotmail.com
 */
public class AdminEntity extends IdEntity{

    /** 当前登录用户 */
    protected LoginUser loginUser;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
}
