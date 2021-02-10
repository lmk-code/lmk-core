package com.lmk.core.web.auth.service;

import javax.servlet.http.HttpServletRequest;
import com.lmk.core.web.auth.bean.LoginUser;

/**
 * 后台用户鉴权服务
 * @author 编程浪子
 * @email laomake@hotmail.com
 */
public interface AdminUserAuthService extends AuthService {

    /**
     * 应用获取登录用户缓存
     * @param request
     * @return
     */
    LoginUser getUserCache(HttpServletRequest request);

    /**
     * 获取登录用户缓存
     * @param token
     * @return
     */
    LoginUser getUserCache(String token);

    /**
     * 保存登录用户到缓存
     * @param user
     * @return
     */
    String saveUserCache(LoginUser user);

    /**
     * 刷新登录用户的缓存时长
     * @param token
     */
    void refreshUserCache(String token);
}
