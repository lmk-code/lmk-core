package com.lmk.core.boot.entity;

import com.lmk.core.web.auth.bean.LoginUser;

import java.util.Date;

/**
 * 管理后台实体
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class AdminEntity extends IdEntity{

    /** 当前登录用户 */
    protected LoginUser loginUser;

    /** 操作时间 */
    protected Date operateTime = new Date();

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
