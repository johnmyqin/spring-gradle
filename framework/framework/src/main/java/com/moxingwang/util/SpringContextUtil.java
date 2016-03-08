package com.moxingwang.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 功能：SpringContext
 */
public class SpringContextUtil implements ApplicationContextAware {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private static ApplicationContext applicationContext;
	/**
	 * 获取spring上下文
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		if (logger.isInfoEnabled()) {
			logger.info("Starting String context....");
		}
		applicationContext = arg0;
	}
	/**
	 * 返回spring上下文
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	/**
	 * 根据bean的名称获取bean的对象
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
	/**
	 * 查找对应名称的bean是否存在
	 * @param beanName
	 * @return
	 */
	public static boolean containsBean(String beanName){
		return applicationContext.containsBean(beanName);
	}

}
