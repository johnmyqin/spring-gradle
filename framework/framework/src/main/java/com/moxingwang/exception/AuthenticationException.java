package com.moxingwang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能：unauthorized
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED)  // 401
public class AuthenticationException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7896489970610466142L;

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 */
	public AuthenticationException(String errCode) {
		super(errCode);
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public AuthenticationException(String errCode, Throwable e) {
		super(errCode, e);
	}

	/**
	 * 构造函数
	 * 
	 * @param e
	 */
	public AuthenticationException(Throwable e) {
		super(e);
	}
}
