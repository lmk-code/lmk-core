package com.lmk.core.commons.jwt.support;

/**
 * JWT 令牌
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class Token {

    /** 头部信息 */
    private Header header;

    /** 有效载荷 */
    private Payload payload;

    /** 签名信息 */
    private String signature;

    public Token() {
    }

    public Token(Header header, Payload payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "{" +
                "header=" + header +
                ", payload=" + payload +
                ", signature='" + signature + '\'' +
                '}';
    }
}
