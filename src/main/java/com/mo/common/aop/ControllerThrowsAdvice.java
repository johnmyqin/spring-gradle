package com.mo.common.aop;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-27 14:56 </p>
 *
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public class ControllerThrowsAdvice implements ThrowsAdvice {
    /**
     * 通知方法，需要按照这种格式书写
     *
     * @param method 可选：切入的方法
     * @param args 可选：切入的方法的参数
     * @param target 可选：目标对象
     * @param ex 必填 : 异常子类，出现这个异常类的子类，则会进入这个通知。
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

        Logger logger = Logger.getLogger(method.getDeclaringClass());

        if(logger.isDebugEnabled()){
            logger.debug(ex.getMessage(),ex);
        }

    }
}
