package com.moxingwang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moxingwang.base.Constants;
import com.moxingwang.core.session.Session;
import com.moxingwang.core.session.SessionManager;
import com.moxingwang.core.session.SessionUtil;
import com.moxingwang.exception.AuthenticationException;

/**
 * 功能：检查access_token
 */
public class AccessInterceptor implements HandlerInterceptor {
	
	private static Logger logger = Logger.getLogger(AccessInterceptor.class);
	
	@Autowired
	private SessionManager sessionManager;
	
//	@Autowired
//	private ComboPooledDataSource  dataSource;
	
	private String loginUrl;

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object resultObject, Exception exception)	throws Exception {

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
		//db test
		//logger.debug("===================DB Connection number:" + dataSource.getNumConnections());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("permissionInterceptor start...");
			logger.debug("=======url:" + request.getRequestURL());
			logger.debug("=======query:" + request.getQueryString());
		}

		//get token from header
		String access_token = SessionUtil.getAccessToken(request);
		Session session = sessionManager.getSession(access_token);
		if (session == null) {			
			//return 401
			String accept = request.getHeader(Constants.ACCEPT);
			if (accept == null || accept.contains("application/json")) {
				throw new AuthenticationException("SYS_ERR_0001");
			} else {
				response.sendRedirect(loginUrl);
				return false;
			}
		} else {
			logger.info("access_token = " + access_token);
			//set session object to request attribute
			request.setAttribute(Constants.SESSION, session);
			return true;
		}
	}

}
