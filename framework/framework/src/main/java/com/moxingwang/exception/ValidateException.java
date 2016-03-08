package com.moxingwang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能：输入检查发现错误时抛出
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)  // 400
public class ValidateException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3842601625786871348L;


}
