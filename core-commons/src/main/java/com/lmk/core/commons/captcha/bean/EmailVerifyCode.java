package com.lmk.core.commons.captcha.bean;

import java.io.Serializable;

/**
 * 邮件验证码
 * @author LaoMake
 * @since 1.0
 *
 */
public class EmailVerifyCode implements Serializable {

    /** 验证码序列号 */
    private String sn;

    /** 目标邮箱 */
    private String email;

    /** 验证码 */
    private String code;

    /** 邮件模板代码 */
    private String templateCode;

    public EmailVerifyCode() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
