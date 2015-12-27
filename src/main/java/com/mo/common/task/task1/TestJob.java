package com.mo.common.task.task1;

import org.apache.log4j.Logger;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-27 14:56 </p>
 *
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public class TestJob {

    private Logger logger = Logger.getLogger(TestJob.class);

    public void execute(){
        try{
            logger.debug("The job is executing ！");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
