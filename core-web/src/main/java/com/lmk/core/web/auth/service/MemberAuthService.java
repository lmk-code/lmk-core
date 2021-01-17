package com.lmk.core.web.auth.service;

import com.lmk.core.web.auth.bean.LoginMember;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员鉴权服务
 */
public interface MemberAuthService extends AuthService {

    /**
     * 应用获取登录会员缓存
     * @param request
     * @return
     */
    LoginMember getMemberCache(HttpServletRequest request);

    /**
     * 获取登录会员缓存
     * @param token
     * @return
     */
    LoginMember getMemberCache(String token);

    /**
     * 保存登录会员到缓存
     * @param member
     * @return
     */
    String saveMemberCache(LoginMember member);

    /**
     * 刷新登录会员的缓存时长
     * @param token
     */
    void refreshMemberCache(String token);
}
