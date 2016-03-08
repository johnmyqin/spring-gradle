package com.moxingwang.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.moxingwang.base.Constants;
import com.moxingwang.core.session.Session;
import com.moxingwang.exception.BaseException;
import com.moxingwang.exception.ServiceException;


@Controller
public class BaseController {
    private Logger logger =Logger.getLogger(BaseController.class);

	public ValidateResult getValidateResult() {
		return new ValidateResult();
	}
	
	public void handleValidateResult(ValidateResult validateResult) {
		if (validateResult != null && validateResult.hasError()) {
			throw validateResult;
		}
	}
	
	public void handleException(Exception e) {
		logger.error(e);
		if (!(e instanceof BaseException)) {
			e.printStackTrace();
			throw new ServiceException(e);            
		} else {
			BaseException be = (BaseException)e;
			if (be.getOriginalException() != null) {
				be.getOriginalException().printStackTrace();
			}
			throw (BaseException)e;
		}
	}
	
	@ModelAttribute
	public void populateModel(HttpServletRequest request, Model model) {  
        model.addAttribute(Constants.SESSION, request.getAttribute(Constants.SESSION));  
     } 
	
	public Session getSession(Model model) {
		return (Session)model.asMap().get(Constants.SESSION);
	}
	
}
