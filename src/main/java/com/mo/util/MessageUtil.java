package com.mo.util;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-26 20:39 </p>
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public class MessageUtil {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static Properties errProp = new Properties();

    private static String messagePth = "messages/message_";
    private static String  suffix = ".properties";

    public void loadMessageCode() {

        if (logger.isInfoEnabled()) {
            logger.info("start init errorCode and errorMessage...");
        }
        String language = Locale.getDefault().toString();
        Resource resource = new ClassPathResource(messagePth + language + suffix);

        try {
            errProp = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String errCode) {
        if (errProp.containsKey(errCode)) {
            return errProp.getProperty(errCode);
        }
        return null;
    }


    public static String getMessage(String errCode, Object... objs) {
        String msg = getMessage(errCode);

        if(msg != null && objs != null) {

            for(int index=0; index<objs.length; index++) {
                Object object = objs[index];

                if(object != null) {
                    msg = StringUtils.replace(msg, "{" + index + "}", object.toString());
                }
            }
        }

        return msg;
    }

}
