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
 * 功能：修改共通header信息，加入rtoken
 */
public class HeaderInterceptor implements HandlerInterceptor {
	
	private static Logger logger = Logger.getLogger(HeaderInterceptor.class);
	
	@Autowired
	private SessionManager sessionManager;


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object resultObject, Exception exception)	throws Exception {

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
//		logger.debug("post HeaderInterceptor start...");
//		//get session object
//		Session session = (Session)request.getAttribute(Constants.SESSION);
//		if (session == null) {
//			String access_token = SessionUtil.getAccessToken(request);
//			session = sessionManager.getSession(access_token);
//		}
//		if (session != null) {
//			String rToken = SessionUtil.generateShortUuid();
//			session.set(Constants.RTOKEN, rToken);
//			response.setHeader(Constants.RTOKEN, rToken);
//			logger.debug("add rtoken:" + rToken);
//		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		logger.debug("pre HeaderInterceptor start...");
		//get session object
		Session session = (Session)request.getAttribute(Constants.SESSION);
		if (session == null) {
			String access_token = SessionUtil.getAccessToken(request);
			session = sessionManager.getSession(access_token);
		}
		if (session != null) {
			//check rtoken
			String rToken = (String)session.get(Constants.RTOKEN);
			//set new token
			String nrToken = SessionUtil.generateShortUuid();
			session.set(Constants.RTOKEN, nrToken);
			response.setHeader(Constants.RTOKEN, nrToken);
			logger.debug("add rtoken:" + nrToken);

			if (rToken != null && !"".equals(rToken)) {
				logger.debug("Check rtoken:" + rToken);
				//get rtoken from request
				String _rtoken = request.getHeader(Constants.RTOKEN);
				if (_rtoken == null) {
					_rtoken = request.getParameter(Constants.RTOKEN);
				}
				if (!rToken.equals(_rtoken)) {
					throw new AuthenticationException("SYS_ERR_0002");
				}
			}
		}
		return true;
	}

}
