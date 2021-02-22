package com.lmk.core.commons.captcha.bean;

import java.io.Serializable;

/**
 * 图片验证码
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class ImageVerifyCode implements Serializable {

    /** 验证码ID */
    private String id;

    /** BASE64编码的图片 */
    private String img;

    public ImageVerifyCode() {
    }

    public ImageVerifyCode(String id, String img) {
        this.id = id;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
