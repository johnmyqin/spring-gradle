package com.mo.util;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-26 20:39 </p>
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public class SingleParamUtil {

	private final Logger logger = Logger.getLogger(this.getClass());

	private static Properties errProp = new Properties();

	private static String errPath = "singleparam.properties";

	public void loadSingleParams() {
		if (logger.isInfoEnabled()) {
			logger.info("start init singleparam...");
		}
		Resource resource = new ClassPathResource(errPath);

		try {
			errProp = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static String getSingleValue(String key) {
		if (errProp.containsKey(key)) {
			return errProp.getProperty(key);
		}
		return null;
	}
	
}
