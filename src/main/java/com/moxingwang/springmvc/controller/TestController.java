package com.moxingwang.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger =Logger.getLogger(TestController.class);

    /**
     * interface test page
     * @param request
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String jspPageTestMain(HttpServletRequest request,ModelMap modelMap) throws Exception {

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;

        modelMap.put("basePath", basePath);

        return "hello";
    }

    /**
     * websocket test page
     * @param request
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/socket", method = RequestMethod.GET)
    public String socketMain(HttpServletRequest request,ModelMap modelMap) throws Exception {

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;

        modelMap.put("basePath", basePath);

        return "socket";
    }

    public static void main(String[] args){
        String ss = "fsdfsd,fsdfs,fsdf,f,";
    }




}
