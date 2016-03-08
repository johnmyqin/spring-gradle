package com.moxingwang.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.moxingwang.core.entity.vo.MessageObj;
import com.moxingwang.core.entity.vo.MessageVo;
import com.moxingwang.util.MessageUtil;
import com.moxingwang.util.SpringContextUtil;

public abstract class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//当status为4xx, 5xx的时候返回错误消息对象
	private MessageVo messages = null;
	
	private Throwable originalException = null;
	
    private MessageUtil messageUtil;
    
	/**
	 * 构造函数
	 */
    public BaseException() {
    	messageUtil = (MessageUtil) SpringContextUtil.getBean("messageUtil"); 
    }
    
	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param message
	 */
	public BaseException(String errCode, String message) {
		super(message);
    	messageUtil = (MessageUtil) SpringContextUtil.getBean("messageUtil"); 
		setMessage(errCode, "", message);
	}
	
	/**
	 * 构造函数
	 * 
	 * @param errCode
	 */
	public BaseException(String errCode) {
    	messageUtil = (MessageUtil) SpringContextUtil.getBean("messageUtil"); 
		String message = messageUtil.getMessage(errCode);
		setMessage(errCode, "", message);
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public BaseException(String errCode, Throwable e) {
		this(errCode, e.getMessage());
		originalException = e;
	}

	/**
	 * 构造函数
	 * 
	 * @param errCode
	 * @param e
	 */
	public BaseException(Throwable e) {
		this("ERR_9999", e.getMessage());
		originalException = e;
	}

    
    public MessageVo getMessageList() {
    	return messages;
    }
    
    public Throwable getOriginalException() {
    	return originalException;
    }

    public void setMessage(String messageCode, String messageObj) {
    	MessageVo messageas = getMessageVo();
    	String objName = messageUtil.getMessage(messageObj);
    	if (objName != null) {
    		messageas.addMessageObj(messageCode, messageUtil.getMessage(messageCode, objName), messageObj);
    	} else {
    		messageas.addMessageObj(messageCode, messageUtil.getMessage(messageCode), messageObj);
    	}
    }
    
    public void setMessage(String messageCode, String messageObj, String message) {
    	MessageVo messageas = getMessageVo();
    	messageas.addMessageObj(messageCode, message, messageObj);
    }
    
    private MessageVo getMessageVo() {
    	if (messages == null)
    		messages = new MessageVo();
		return messages;

    }
    
	/**
	 * 获取消息字符串
	 * 
	 * @return 消息字符串
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		StringBuffer buf = new StringBuffer();
		buf.append(getClass().getName()).append(":\r\n");
		if (messages != null) {
			for(MessageObj msg:messages.getReasons()) {
				if (msg.getMessage_id() != null) {
					buf.append("ErrorCode:[").append(msg.getMessage_id()).append("]")
							.append("\r\n");
				}
				buf.append("ErrorMesg:[").append(msg.getMessage()).append("]")
						.append("\r\n");

			}
		}

		return buf.toString();
	}

	/**
	 * 异常实例转String，返回消息字符串
	 * 
	 * @return 消息字符串
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getMessage();
	}

    
	public static String get(Throwable t) {
		//
		if (t == null) {
			return "NULL";
		}
		// Create
		StringBuffer b = new StringBuffer();
		//
		b.append(t.getMessage());
		b.append("\n");
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		//
		t.printStackTrace(ps);
		//
		b.append(baos.toString());
		//
		return b.toString();
	}
}
