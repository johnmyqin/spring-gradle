package com.moxingwang.util;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 功能：properties文件读取
 */
public class ParamUtil {

	private Logger logger = Logger.getLogger(ParamUtil.class);

	private static Properties paramProp = new Properties();

	private static String path = "META-INF/config/param.properties";
	@PostConstruct
	public void paramInit() {
		if (logger.isInfoEnabled()) {
			logger.info("param initial is start...");
		}
		Resource resource = new ClassPathResource(path);
		try {
			paramProp = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getParamValue(String paramKey) {
		if (paramProp.containsKey(paramKey)) {
			return paramProp.getProperty(paramKey);
		}
		return null;
	}

}
