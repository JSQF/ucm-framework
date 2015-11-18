package com.saike.ucm.client.web;

import com.saike.ucm.client.UcmClient;
import com.saike.ucm.client.context.UcmContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by huawei on 11/18/15.
 */
public class UcmContextLoaderListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(UcmContextLoaderListener.class);

    private static final String UCM_DEFAULT_CONTEXT_CONFIG_LOCATION = "classpath:ucm.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String ucmContextConfigLocation = sce.getServletContext().getInitParameter("ucmContextConfigLocation");
        if (ucmContextConfigLocation == null) {
            ucmContextConfigLocation = UCM_DEFAULT_CONTEXT_CONFIG_LOCATION;
        }
        try{
            UcmClient client = new UcmClient(ucmContextConfigLocation);
            client.load();
            UcmContextHolder.getHolder().printAll();
        }catch (Exception e) {

            logger.error("ucm load error", e);

        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
