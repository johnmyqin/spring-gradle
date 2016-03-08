package com.moxingwang.core.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encode {

	static Cipher cipher;
	static final String KEY_ALGORITHM = "AES";
	/* 
	 *  
	 */
	static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

	/**
	 * 字符串加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM); // KeyGenerator提供（对称）密钥生成器的功能。使用getInstance
																			// 类方法构造密钥生成器。
			kgen.init(128, new SecureRandom(password.getBytes()));// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);// 使用SecretKeySpec类来根据一个字节数组构造一个
																				// SecretKey,，而无须通过一个（基于
																				// provider
																				// 的）SecretKeyFactory.
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);// 创建密码器
																		// //为创建
																		// Cipher
																		// 对象，应用程序调用
																		// Cipher
																		// 的
																		// getInstance
																		// 方法并将所请求转换
																		// 的名称传递给它。还可以指定提供者的名称（可选）。
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV()));// 初始化
			byte[] result = cipher.doFinal(byteContent); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
															// Cipher 的初始化方式）。
			return base64Encode(result); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()));// 初始化
			byte[] encrypt = base64Decode(content);
			byte[] result = cipher.doFinal(encrypt);
			return new String(result, "utf-8"); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getIV() {
		String iv = "dki2-049ldL;+&QM"; // must be 16 byte
		return iv.getBytes();
	}

	/**
	 * Base64 编码
	 * 
	 * @param content
	 * @return
	 */
	public static String base64Encode(byte[] content) {
		byte[] result = Base64.encodeBase64(content);
		return new String(result);
	}

	/**
	 * Base64解码
	 * 
	 * @param content
	 * @return
	 */
	public static byte[] base64Decode(String content) {
		return Base64.decodeBase64(content);
	}

	/**
	 * byte数组转字符串
	 * @param content
	 * @return
	 */
	public static String hexString(byte[] content) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < content.length; i++) {
			int val = (int) content[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String SHA256(String content) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes("utf-8"));
		byte[] result = md.digest();
		return hexString(result);
	}
}
