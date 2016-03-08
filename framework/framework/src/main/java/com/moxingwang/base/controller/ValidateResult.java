package com.moxingwang.base.controller;

import com.moxingwang.exception.ValidateException;

public class ValidateResult extends ValidateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1199021389779748721L;
	
	public boolean hasError() {
		if (getMessageList() != null)
			return true;
		else
			return false;
	}
}
