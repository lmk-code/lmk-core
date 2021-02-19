package com.lmk.core.commons.jwt;

import com.lmk.core.commons.jwt.support.Header;
import com.lmk.core.commons.jwt.support.Payload;
import com.lmk.core.commons.jwt.support.Token;
import com.lmk.core.commons.utils.Encodes;
import com.lmk.core.commons.utils.JsonUtils;

/**
 * JWT工具类
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class JwtUtils {

    /**
     * 生成令牌，有效期24小时
     * @param secret    签名密钥
     * @param id        用户ID
     * @param username  用户名
     * @param nickname  用户昵称
     * @return
     */
    public static String encodeToken(String secret, Integer id, String username, String nickname){
        Header header = new Header();
        Payload payload = new Payload(id, username, nickname);
        payload.setExpire(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        StringBuilder sb = new StringBuilder();
        sb.append(Encodes.toBase64(header.toString()));
        sb.append(".").append(Encodes.toBase64(payload.toString()));

        String signature = sign(sb.toString(), secret);
        sb.append(".").append(signature);

        return sb.toString();
    }

    /**
     * 解密Token字符串
     * @param source    源字符串
     * @param secret    混淆密钥
     * @return  解密成功，返回Token对象；解密失败，则返回 null
     */
    public static Token decodeToken(String source, String secret){
        String signature = checkSignature(source, secret);
        if(signature != null){
            String[] data = source.split("\\.");
            Header header = JsonUtils.parseObject(Encodes.fromBase64(data[0]), Header.class);
            Payload payload = JsonUtils.parseObject(Encodes.fromBase64(data[1]), Payload.class);

            return new Token(header, payload, signature);
        }
        return null;
    }

    /**
     * 对自定字符串进行混淆加密
     * @param data
     * @param secret
     * @return
     */
    private static String sign(String data, String secret){
        return Encodes.sha256(data + secret);
    }

    /**
     * 校验签名
     * @param secret
     * @param token
     * @return 校验成功，返回有效签名；校验失败则返回null
     */
    private static String checkSignature(String token, String secret){
        int index = token.lastIndexOf(".");
        String data = token.substring(0, index);
        String signature = token.substring(index + 1);
        String sign = sign(data, secret);
        return sign.equals(signature) ? signature : null;
    }

}
