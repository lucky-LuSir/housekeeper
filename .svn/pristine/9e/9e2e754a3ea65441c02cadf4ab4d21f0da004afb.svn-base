/**  
 * @Title: SecurityUtils.java 
 * @Package com.warmdoctor.ows.core.util 
 * @author bruce  
 * @date 2015年4月29日 下午1:19:17 
 * @version V1.0  
 */
package com.kfwy.hkp.common.utils;



import cn.hutool.core.codec.Base64Encoder;
import com.kfwy.hkp.common.auth.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息加密操作
 * 
 * @ClassName: SecurityUtils
 * @author bruce
 * @date 2015年4月29日 下午1:19:17
 */
public class SecurityUtils {

	/**
	 * @Fields SHA_1 : SHA_1模式
	 */
	public static final String SHA_1 = "SHA-1";

	/**
	 * @Fields MD5 : MD5模式
	 */
	public static final String MD5 = "MD5";
	private static String str;

	/**
	 * 加密MD5信息
	 * 
	 * @author :bruce
	 * @param str
	 * @return String
	 * @create 2015年4月29日下午1:22:08
	 */
	public static String md5(String str) {
		SecurityUtils.str = str;
		return encode(str, MD5);
	}

	/**
	 * 加密SHA-1信息
	 * 
	 * @author :bruce
	 * @param str
	 * @return String
	 * @create 2015年4月29日下午1:23:31
	 */
	public static String sha1(String str) {
		return encode(str, SHA_1);
	}

	/**
	 * Base64编码
	 * 
	 * @author :bruce
	 * @param str
	 * @return String
	 * @create 2015年5月8日下午9:20:59
	 */
	public static String base64Encoder(String str) {
		try {
			Base64Encoder encoder = new Base64Encoder();
			return encoder.encode(str.getBytes("utf-8"));
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 新浪加密
	 * 
	 * @author :bruce
	 * @param str
	 * @return String
	 * @create 2015年5月7日上午9:31:27
	 */
	public static String sina(String str) {
		Encryption ep = new Encryption();
		return ep.getDigestOfString(str);
	}

	/**
	 * 加密指定的类型
	 * 
	 * @author :bruce
	 * @param str
	 * @param algorithm
	 * @return String
	 * @create 2015年4月29日下午1:22:20
	 */
	public static String encode(String str, String algorithm) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = str.getBytes();
		try {
			if (algorithm == null || "".equals(algorithm))
				// 默认使用MD5
			{
				algorithm = MD5;
			}
			md = MessageDigest.getInstance(algorithm);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return str;
		}
		return strDes;
	}

	/**
	 * 将加密的信息进行格式转换
	 * 
	 * @author :bruce
	 * @param bts
	 * @return String
	 * @create 2015年4月29日下午1:23:59
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

}
