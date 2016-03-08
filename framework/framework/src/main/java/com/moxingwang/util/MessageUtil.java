package com.moxingwang.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * 功能：消息多语言，基于SpringMVC
 */
@Service
public class MessageUtil {

    private static Logger logger = Logger.getLogger(MessageUtil.class);

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    private Locale getLanguage(){
        Locale language = Locale.getDefault();

        return language;
    }

    public String getMessage(String msg) {

        String s = "";

        try{
            s = messageSource.getMessage(msg, new Object[]{}, getLanguage());
        }catch (Exception e){
            if(logger.isDebugEnabled()){
                logger.debug("message not found");
            }
        }

        return s;
    }
    
    public String getMessage(String msg, Locale language) {

        String s = "";

        try{
            s = messageSource.getMessage(msg, new Object[]{}, language);
        }catch (Exception e){
            if(logger.isDebugEnabled()){
                logger.debug("message not found");
            }
        }

        return s;
    }

    public String getMessage(String errCode ,Object... objs) {

        String s = "";

        try{
            s = messageSource.getMessage(errCode, objs, getLanguage());
        }catch (Exception e){
            if(logger.isDebugEnabled()){
                logger.debug("message not found");
            }
        }

        return s;
    }
}
