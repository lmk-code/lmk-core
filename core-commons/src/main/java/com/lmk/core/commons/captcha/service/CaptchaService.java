package com.lmk.core.commons.captcha.service;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.lmk.core.commons.captcha.bean.ImageVerifyCode;
import com.lmk.core.commons.utils.CaptchaUtils;
import com.lmk.core.commons.utils.Encodes;

/**
 * 验证码服务实现，需要手动导入到IOC容器
 *
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class CaptchaService {

    /**
     * 缓存服务
     */
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 缓存key的前缀
     */
    private String keyPrefix = "captcha_";

    /**
     * 验证码的长度
     */
    private int defaultCharLength = 4;

    /**
     * 生成图片验证码，默认为4个字符
     * @param token         缓存key
     * @param width         图片宽度
     * @param height        图片高度
     * @return
     */
    public ImageVerifyCode getImageVerifyCode(String token, int width, int height) {
        return getImageVerifyCode(token, width, height, defaultCharLength);
    }

    /**
     * 生成图片验证码
     * @param token         缓存key
     * @param width         图片宽度
     * @param height        图片高度
     * @param charLength    字符个数
     * @return
     */
    public ImageVerifyCode getImageVerifyCode(String token, int width, int height, int charLength) {
        String code = Encodes.nonceString(charLength);

        // 设置验证码
        ImageVerifyCode imageVerifyCode = new ImageVerifyCode();
        imageVerifyCode.setCode(code);

        // 将验证码添加到缓存，默认10分钟有效
        String key = keyPrefix + "img_" + token;
        redisTemplate.opsForValue().set(key, imageVerifyCode, 10, TimeUnit.MINUTES);

        // 清除原始的验证码，避免泄露
        imageVerifyCode.setCode(null);

        // 添加图片数据
        imageVerifyCode.setImg(CaptchaUtils.createBase64(code, width, height));

        return imageVerifyCode;
    }

    /**
     * 校验图片验证码
     * @param token     缓存key
     * @param code      用户输入的代码
     * @return
     */
    public boolean checkImageVerifyCode(String token, String code) {
        boolean pass = false;

        // 查询缓存
        String key = keyPrefix + "img_" + token;
        ImageVerifyCode imageVerifyCode = (ImageVerifyCode) redisTemplate.opsForValue().get(key);

        // 校验验证码
        if (imageVerifyCode != null && imageVerifyCode.getCode().equals(code)) {
            pass = true;
        }

        return pass;
    }
}
