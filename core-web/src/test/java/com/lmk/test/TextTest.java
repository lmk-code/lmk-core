package com.lmk.test;

import org.junit.jupiter.api.Test;
import com.lmk.core.commons.utils.Encodes;

/**
 * 文本测试
 */
public class TextTest {

    @Test
    public void testSortStr(){
        String str = "user_name_desc";
        int index = str.lastIndexOf("_");
        str = str.substring(0, index) + " " + str.substring(index + 1);
        System.out.println(str);
    }

    @Test
    public void testPassword(){
        String plainPassword = "123456";
        String salt = Encodes.salt();
        String password = Encodes.password(plainPassword, salt);

        System.out.println("明文密码：" + plainPassword);
        System.out.println("混淆字符：" + salt);
        System.out.println("密文密码：" + password);
    }

    @Test
    public void testClassname(){
        String fullname = "com.lmk.test.TextTest";
        Class<?> clazz = this.getClass();
        String packageName = clazz.getPackage().getName();
        packageName = packageName.substring(packageName.lastIndexOf(".") + 1);

        System.out.println(packageName);



    }

}
