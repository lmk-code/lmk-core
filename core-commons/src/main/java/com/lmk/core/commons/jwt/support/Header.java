package com.lmk.core.commons.jwt.support;

import com.lmk.core.commons.utils.JsonUtils;

/**
 * 头部信息
 *
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class Header {
    /**
     * 令牌类型
     */
    String type;

    /**
     * 加密算法
     */
    String alg;

    public Header() {
        type = "JWT";
        alg = "HS256";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    @Override
    public String toString() {
        return JsonUtils.toJSON(this);
    }
}
