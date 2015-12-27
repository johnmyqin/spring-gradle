package com.mo.controller.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-27 17:28 </p>
 *
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
@Controller
@RequestMapping("/test")
public class CommonPagesController {

    private Logger logger = Logger.getLogger(CommonPagesController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String websocket(ModelMap modelMap,HttpServletRequest request) {

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;

        logger.debug("test page !");

        modelMap.put("basePath", basePath);

        return "hello";
    }

}
