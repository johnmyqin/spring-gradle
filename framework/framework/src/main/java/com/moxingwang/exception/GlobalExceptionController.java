package com.moxingwang.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.moxingwang.core.entity.vo.MessageVo;

@ControllerAdvice
public class GlobalExceptionController {
	private static Logger logger =Logger.getLogger(GlobalExceptionController.class);
  
  
    @ExceptionHandler(ValidateException.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
    @ResponseBody
    public MessageVo handleValidateException(HttpServletRequest request, ValidateException ex) {  
        return ex.getMessageList(); 
    } 
    
    @ExceptionHandler(ServiceException.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
    @ResponseBody
    public MessageVo handleServiceException(HttpServletRequest request, ServiceException ex) {  
        return ex.getMessageList(); 
    } 
    
    @ExceptionHandler(AuthenticationException.class)  
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)  
    @ResponseBody
    public MessageVo handleServiceException(HttpServletRequest request, AuthenticationException ex) {  
        return ex.getMessageList(); 
    } 
    
    @ExceptionHandler(NotFoundException.class)  
    @ResponseStatus(value = HttpStatus.NOT_FOUND)  
    @ResponseBody
    public MessageVo handleNotFoundException(HttpServletRequest request, NotFoundException ex) {  
        return ex.getMessageList(); 
    } 
    
    @ExceptionHandler(Exception.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
    @ResponseBody
    public MessageVo handleException(HttpServletRequest request, Exception ex) {  
    	ex.printStackTrace();
    	MessageVo messages = new MessageVo();
    	messages.addMessageObj("SYS_ERR_9999", ex.getMessage(), "");
        return messages; 
    } 
}
