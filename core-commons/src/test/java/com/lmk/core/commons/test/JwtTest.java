package com.lmk.core.commons.test;

import org.junit.jupiter.api.Test;
import com.lmk.core.commons.jwt.JwtUtils;
import com.lmk.core.commons.jwt.support.Token;

/**
 * JWT 令牌测试
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class JwtTest {

    @Test
    public void testGetToken(){
        String secret = "LaoMake";
        Integer id = 1;
        String username = "admin";
        String nickname = "超级管理员";

        String token = JwtUtils.encodeToken(secret, id, username, nickname);
        System.out.println("Token: " + token);
    }

    @Test
    public void testParseToken(){
        String secret = "LaoMake";
        String source = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ==.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsIm5pY2tuYW1lIjoi6LaF57qn566h55CG5ZGYIiwiZXhwaXJlIjoxNjEzNzkyODUwMTQ0fQ==.97e5edf92b9749e9220cc1bee48d3fc877813993605cd64d63318137a621608b";

        Token token = JwtUtils.decodeToken(source, secret);
        System.out.println("token: " + token);
    }

}
