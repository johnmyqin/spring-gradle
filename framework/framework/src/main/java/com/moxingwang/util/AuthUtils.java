package com.moxingwang.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

import com.moxingwang.core.encrypt.Encode;

/**
 * 功能：授权相关的工具类
 */
public class AuthUtils {

	/**
	 * 加密密码
	 * @param password 
	 * @param salt
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encodePassword(String password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String ps = password + salt;
		return Encode.SHA256(ps);
	}

	/**
	 * @param str
	 * @return
	 * @deprecated
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str).toLowerCase();
	}
	
	public static void main(String[] args){
		String name = "admin";
		String password = "111111";
		System.out.println("the password after encrypt..." + DigestUtils.md5Hex(password + name).toLowerCase());
	}
	
}
