package com.moxingwang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 系统异常
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)  // 400
public class ServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736010255834785196L;

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 */
	public ServiceException(String errCode) {
		super(errCode);
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public ServiceException(String errCode, Throwable e) {
		super(errCode, e);
	}

	/**
	 * 构造函数
	 * 
	 * @param e
	 */
	public ServiceException(Throwable e) {
		super(e);
	}
	
	@Deprecated
	public String getErrCode() {
		return "";
	}
}
