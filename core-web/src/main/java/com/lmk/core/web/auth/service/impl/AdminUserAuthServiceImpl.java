package com.lmk.core.web.auth.service.impl;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.lmk.core.commons.cache.service.CacheService;
import com.lmk.core.commons.utils.Encodes;
import com.lmk.core.web.auth.bean.AuthKeys;
import com.lmk.core.web.auth.bean.LoginUser;
import com.lmk.core.web.auth.service.AdminUserAuthService;

/**
 * 后台接口鉴权安全服务
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class AdminUserAuthServiceImpl implements AdminUserAuthService {

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
        }while(cacheService.hasKey(AuthKeys.ADMIN_USER + token));

        return token;
    }

    @Override
    public LoginUser getUserCache(HttpServletRequest request) {
        String token = getToken(request);
        return getUserCache(token);
    }

    @Override
    public LoginUser getUserCache(String token) {
        LoginUser user = null;
        if(StringUtils.isNotBlank(token)){
            if(cacheService.hasKey(AuthKeys.ADMIN_USER + token)){
                cacheService.refresh(AuthKeys.ADMIN_USER + token, 30, TimeUnit.MINUTES);
                user = (LoginUser) cacheService.get(AuthKeys.ADMIN_USER + token);
            }
        }
        return user;
    }

    @Override
    public String saveUserCache(LoginUser user) {
        String token = generateToken();
        cacheService.set(AuthKeys.ADMIN_USER + token, user);
        refreshUserCache(token);
        return token;
    }

    @Override
    public void refreshUserCache(String token){
        if(cacheService.hasKey(AuthKeys.ADMIN_USER + token)){
            cacheService.refresh(AuthKeys.ADMIN_USER + token, 30, TimeUnit.MINUTES);
        }
    }
}
