package com.lmk.core.commons.jwt.support;

import com.lmk.core.commons.utils.JsonUtils;

/**
 * 有效载荷
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class Payload {

    /** 用户ID */
    private Integer id;

    /** 用户名 */
    private String username;

    /** 用户昵称 */
    private String nickname;

    /** 失效时间 */
    private Long expire;


    public Payload() {
    }

    public Payload(Integer id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return JsonUtils.toJSON(this);
    }
}
