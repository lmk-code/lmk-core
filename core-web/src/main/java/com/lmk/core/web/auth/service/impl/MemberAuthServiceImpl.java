package com.lmk.core.web.auth.service.impl;

import com.lmk.core.web.auth.bean.AuthKeys;
import com.lmk.core.web.auth.bean.LoginMember;
import com.lmk.core.web.auth.service.MemberAuthService;
import com.lmk.core.commons.cache.service.CacheService;
import com.lmk.core.commons.utils.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 会员鉴权服务实现
 * @author LaoMake
 * @since 1.0
 */
public class MemberAuthServiceImpl implements MemberAuthService {

    @Autowired
    CacheService cacheService;

    /**
     * 生成Token
     * @return
     */
    public synchronized String generateToken(){
        String token = null;

        do{
            token = Encodes.nonceString(32);
        }while(cacheService.hasKey(AuthKeys.MEMBER_LOGIN + token));

        return token;
    }

    @Override
    public LoginMember getMemberCache(HttpServletRequest request) {
        String token = getToken(request);
        return getMemberCache(token);
    }

    @Override
    public LoginMember getMemberCache(String token) {
        LoginMember member = null;

        if(StringUtils.isNotBlank(token)){
            if(cacheService.hasKey(AuthKeys.MEMBER_LOGIN + token)){
                cacheService.refresh(AuthKeys.MEMBER_LOGIN + token, 30, TimeUnit.MINUTES);
                member = (LoginMember) cacheService.get(AuthKeys.MEMBER_LOGIN + token);
            }
        }
        return member;
    }

    @Override
    public String saveMemberCache(LoginMember member) {
        String token = generateToken();
        cacheService.set(AuthKeys.MEMBER_LOGIN + token, member);
        refreshMemberCache(token);
        return token;
    }

    @Override
    public void refreshMemberCache(String token) {
        if(cacheService.hasKey(AuthKeys.MEMBER_LOGIN + token)){
            cacheService.refresh(AuthKeys.MEMBER_LOGIN + token, 30, TimeUnit.MINUTES);
        }
    }
}
