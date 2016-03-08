package com.moxingwang.base.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moxingwang.core.session.SessionManager;

/**
 * 功能：跳转到JSP
 */
@Controller
@RequestMapping("/forward")
public class ForwardController extends BaseController {

    private Logger logger =Logger.getLogger(ForwardController.class);

	@Autowired
	private SessionManager sessionManager;

    /**
     * @throws Exception 
     
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public String forward(@RequestParam String url) {
    	return url;
    }
}
