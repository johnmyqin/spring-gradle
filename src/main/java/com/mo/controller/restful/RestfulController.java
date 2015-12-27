package com.mo.controller.restful;

import com.mo.entity.user.UserBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-26 20:39 </p>
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
@Controller
@RequestMapping("/rest")
public class RestfulController {

    private Logger logger = Logger.getLogger(RestfulController.class);

    @ResponseBody
    @RequestMapping(value = "/aa/{bb}",method = RequestMethod.GET)
    public Object testRestGet(@PathVariable String bb){
        logger.debug("coming !");
        return "json" + bb;
    }

    @ResponseBody
    @RequestMapping(value = "/aa/{bb}",method = RequestMethod.POST)
    public Object testRestPost(@PathVariable String bb,@RequestBody UserBean userBean){
        logger.debug(userBean);
        return "json" + bb + userBean.toString();
    }

}
