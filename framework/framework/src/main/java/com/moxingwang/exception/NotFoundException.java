package com.moxingwang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能：查询结果为空时使用
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)  // 404
public class NotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1578513387529690262L;

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 */
	public NotFoundException(String errCode) {
		super(errCode);
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public NotFoundException(String errCode, Throwable e) {
		super(errCode, e);
	}

	/**
	 * 构造函数
	 * 
	 * @param e
	 */
	public NotFoundException(Throwable e) {
		super(e);
	}

}
