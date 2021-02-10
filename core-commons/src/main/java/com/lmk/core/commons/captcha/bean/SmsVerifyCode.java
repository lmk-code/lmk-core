package com.lmk.core.commons.captcha.bean;

import java.io.Serializable;

/**
 * 短信验证码
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class SmsVerifyCode implements Serializable {

    /** 验证码序列号 */
    private String sn;

    /** 目标手机号 */
    private String mobile;

    /** 验证码 */
    private String code;

    /** 短信模板代码 */
    private String templateCode;

    public SmsVerifyCode() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
