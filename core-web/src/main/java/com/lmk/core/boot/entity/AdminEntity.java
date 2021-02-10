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

    /** 创建时间 */
    protected Date createTime;

    /** 修改时间 */
    protected Date updateTime;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
