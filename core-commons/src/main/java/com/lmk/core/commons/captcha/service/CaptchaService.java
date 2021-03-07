package com.lmk.core.commons.captcha.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import static com.google.code.kaptcha.Constants.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.FastByteArrayOutputStream;
import com.lmk.core.commons.captcha.bean.ImageVerifyCode;
import com.lmk.core.commons.utils.IdUtils;

/**
 * 验证码服务实现，需要手动导入到IOC容器
 *
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class CaptchaService {

    /** 文本验证码生成器 */
    private static DefaultKaptcha textDefaultCaptcha;

    /** 算术运算验证码生成器 */
    private static DefaultKaptcha mathDefaultCaptcha;

    /**
     * 缓存服务
     */
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 缓存key的前缀
     */
    private String preKey = "Captcha-";

    /**
     * 验证码的长度
     */
    private int defaultCharLength = 6;

    /**
     * 校验图片验证码
     * @param id
     * @param code
     * @return
     */
    public boolean checkImageVerifyCode(String id, String code){
        String cacheCode = (String) redisTemplate.opsForValue().get(preKey + id);
        redisTemplate.delete(preKey + id);
        if(cacheCode != null && cacheCode.equals(code)){
            return true;
        }
        return false;
    }

    /**
     * 封装、返回验证码对象
     * @param code
     * @param image
     * @return
     * @throws IOException
     */
    private ImageVerifyCode buildVerifyCode(String code, BufferedImage image) throws IOException {
        // 验证码ID
        String uuid = IdUtils.uuid();

        // 将验证码结果保存到缓存，5分钟内有效
        redisTemplate.opsForValue().set(preKey + uuid, code, 5, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);

        return new ImageVerifyCode(uuid, "data:image/jpg;base64," + Base64.encodeBase64String(os.toByteArray()));
    }

    /**
     * 获取常规字符验证的验证码
     * @return
     */
    public ImageVerifyCode textCaptcha() throws IOException {
        // 字符验证码
        String code = getTextCaptchaBean().createText();

        // 图片对象
        BufferedImage image = getTextCaptchaBean().createImage(code);

        return buildVerifyCode(code, image);
    }

    /**
     * 获取算术运算验证的验证码
     * @return
     */
    public ImageVerifyCode mathCaptcha() throws IOException {

        // 算术运算表达式 含结果
        String fullText = getMathCaptchaBean().createText();

        //算术运算表达式 结果
        String code = fullText.substring(fullText.lastIndexOf("@") + 1);

        // 算术运算表达式 不含结果
        String capStr = fullText.substring(0, fullText.lastIndexOf("@"));

        // 图片对象
        BufferedImage image = getMathCaptchaBean().createImage(capStr);

        return buildVerifyCode(code, image);
    }

    private DefaultKaptcha getTextCaptchaBean(){
        if(textDefaultCaptcha == null){
            synchronized (CaptchaService.class){
                if(textDefaultCaptcha == null){
                    textDefaultCaptcha = new DefaultKaptcha();
                    Properties properties = new Properties();
                    // 是否有边框 默认为true 我们可以自己设置yes，no
                    properties.setProperty(KAPTCHA_BORDER, "no");
                    // 验证码文本字符颜色 默认为Color.BLACK
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
                    // 验证码图片宽度 默认为200
                    properties.setProperty(KAPTCHA_IMAGE_WIDTH, "120");
                    // 验证码图片高度 默认为50
                    properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "40");
                    // 验证码文本字符大小 默认为40
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "34");
                    // KAPTCHA_SESSION_KEY
                    properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
                    // 验证码文本字符长度 默认为5
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
                    // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
                    // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
                    properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
                    Config config = new Config(properties);
                    textDefaultCaptcha.setConfig(config);
                }
            }
        }
        return textDefaultCaptcha;
    }

    private DefaultKaptcha getMathCaptchaBean(){
        if(mathDefaultCaptcha == null){
            synchronized (CaptchaService.class){
                if(mathDefaultCaptcha == null){
                    mathDefaultCaptcha = new DefaultKaptcha();
                    Properties properties = new Properties();
                    // 是否有边框 默认为true 我们可以自己设置yes，no
                    properties.setProperty(KAPTCHA_BORDER, "no");
                    // 边框颜色 默认为Color.BLACK
                    properties.setProperty(KAPTCHA_BORDER_COLOR, "160,160,160");
                    // 验证码文本字符颜色 默认为Color.BLACK
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
                    // 验证码图片宽度 默认为200
                    properties.setProperty(KAPTCHA_IMAGE_WIDTH, "120");
                    // 验证码图片高度 默认为50
                    properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "40");
                    // 验证码文本字符大小 默认为40
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "34");
                    // KAPTCHA_SESSION_KEY
                    properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
                    // 验证码文本生成器
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, "com.lmk.core.commons.captcha.service.CaptchaTextCreator");
                    // 验证码文本字符间距 默认为2
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
                    // 验证码文本字符长度 默认为5
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
                    // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
                    properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
                    // 验证码噪点颜色 默认为Color.BLACK
                    properties.setProperty(KAPTCHA_NOISE_COLOR, "white");
                    // 干扰实现类
                    properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
                    // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
                    properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
                    Config config = new Config(properties);
                    mathDefaultCaptcha.setConfig(config);
                }
            }
        }
        return mathDefaultCaptcha;
    }

}
