package com.lmk.core.commons.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 单向加密工具类
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class Encodes{

	/** 随机字符集合( 不包含大写字母，并移除字母：o、l，避免视觉混淆 ) */
	private static String NONCE_STRING = "abcdefghijkmnpqrstuvwxyz0123456789";

	/** 密码salt字节数组长度 */
	private static int SALT_SIZE = 8;

	/** 密码加密类型 */
	public static final String PASSWORD_ALGORITHM = "SHA-256";

	/** 密码散列次数 */
	public static int PASSWORD_INTERATIONS = 1024;

	/**
	 * 对字符串进行MD5加密
	 * @param source
	 * @return
	 */
	public static String md5(String source){
		return DigestUtils.md5Hex(source);
	}

	/**
	 * 对字节数组进行MD5加密
	 * @param data
	 * @return
	 */
	public static String md5(byte[] data){
		return DigestUtils.md5Hex(data);
	}

	/**
	 * 对字符串进行简单的SHA1加密
	 * @param source
	 * @return
	 */
	public static String sha1(String source){
		return DigestUtils.sha1Hex(source);
	}

	/**
	 * 对字节数组进行简单的SHA1加密
	 * @param source
	 * @return
	 */
	public static byte[] sha1(byte[] source){
		return DigestUtils.sha1(source);
	}

	/**
	 * 对字符串进行简单的SHA256加密
	 * @param source
	 * @return
	 */
	public static String sha256(String source){
		return DigestUtils.sha256Hex(source);
	}

	/**
	 * 对字节数组进行简单的SHA256加密
	 * @param source
	 * @return
	 */
	public static byte[] sha256(byte[] source){
		return DigestUtils.sha256(source);
	}

	/**
	 * Hex编码
	 * @param data
	 * @return
	 */
	public static String hexEncode(byte[] data) {
		return Hex.encodeHexString(data);
	}

	/**
	 * Hex解码
	 * @param source
	 * @return
	 */
	public static byte[] hexDecode(String source) {
		byte[] result = null;
		try {
			result = Hex.decodeHex(source.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取字符串的UTF-8字节数组
	 * @param source
	 * @return
	 */
	public static byte[] getBytesUtf8(String source) {
		return org.apache.commons.codec.binary.StringUtils.getBytesUtf8(source);
	}

	/**
	 * 将Base64字符串解析为字节数组
	 * @param source
	 * @return
	 */
	public static byte[] getBytesBase64(String source) {
		return Base64.decodeBase64(source);
	}

	/**
	 * 根据字节数组构造UTF-8字符串
	 * @param source
	 * @return
	 */
	public static String getStringUtf8(byte[] source) {
		return org.apache.commons.codec.binary.StringUtils.newStringUtf8(source);
	}

	/**
	 * 根据字节数组构造Base64字符串
	 * @param source
	 * @return
	 */
	public static String getStringBase64(byte[] source){
		return Base64.encodeBase64String(source);
	}

	/**
	 * 对UTF-8编码的字符串进行Base64编码
	 * @param source
	 * @return
	 */
	public static String toBase64(String source){
		return Base64.encodeBase64String(getBytesUtf8(source));
	}

	/**
	 * 将Base64编码的字符串解析为UTF-8编码的字符串
	 * @param source
	 * @return
	 */
	public static String fromBase64(String source){
		return getStringUtf8(getBytesBase64(source));
	}

	/**
	 * 生成随机salt
	 * @return
	 */
	public static String salt() {
		byte[] bytes = new byte[SALT_SIZE];
		RandomUtils.nextBytes(bytes);
		return hexEncode(bytes);
	}

	/**
	 * 生成安全密码
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String password(String password, String salt){
		MessageDigest digest = DigestUtils.getSha256Digest();
		digest.update(hexDecode(salt));

		byte[] result = digest.digest(password.getBytes());

		for (int i = 1; i < PASSWORD_INTERATIONS; i++) {
			digest.reset();
			result = digest.digest(result);
		}
		
		return Hex.encodeHexString(result);
	}

	/**
	 * 获取随机字符串
	 * @param length
	 * 			字符串长度
	 * @return
	 */
	public static String nonceString(Integer length) {
		int index;
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			index = RandomUtils.nextInt(NONCE_STRING.length());
			sb.append(NONCE_STRING.charAt(index));
		}
		return sb.toString();
	}
	
	/**
	 * 生成验证码：6位整数
	 * @return
	 */
	public static String verifyCode() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <6; i++)
			sb.append(RandomUtils.nextInt(10));
		
		return sb.toString();
	}
}
