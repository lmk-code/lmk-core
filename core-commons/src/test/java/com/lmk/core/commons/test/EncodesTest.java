package com.lmk.core.commons.test;

import org.junit.jupiter.api.Test;
import com.lmk.core.commons.utils.Encodes;

/**
 * 简单的加密、解密测试
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class EncodesTest {

    @Test
    public void testPassword(){
        String plainPassword = "123456";
        String salt = Encodes.salt();
        String password = Encodes.password(plainPassword, salt);

        System.out.println("明文密码：" + plainPassword);
        System.out.println("混淆字符：" + salt);
        System.out.println("密文密码：" + password);

    }

}
