package com.lmk.core.commons.captcha.bean;

import java.io.Serializable;

/**
 * 图片验证码
 * @author LaoMake
 * @since 1.0
 *
 */
public class ImageVerifyCode implements Serializable {

    /** 验证码 */
    private String code;

    /** BASE64编码的图片 */
    private String img;

    public ImageVerifyCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
