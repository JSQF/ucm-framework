package com.saike.ucm.venus;

import com.meidusa.toolkit.common.runtime.Application;
import com.meidusa.toolkit.common.runtime.ApplicationConfig;
import com.saike.ucm.client.UcmClient;
import com.saike.ucm.client.exception.ClientException;

public class UcmApplication extends Application<ApplicationConfig> {

    public static void main(String[] args) {
        System.setProperty(ApplicationConfig.PROJECT_MAINCLASS, UcmApplication.class.getName());
        UcmClient client = null;
        try {
            client = new UcmClient("file:${project.home}/ucm.properties");
            client.load();
        } catch (ClientException e) {

        }
        Application.main(args);
    }

    @Override
    public void doRun() {

    }

    @Override
    public ApplicationConfig getApplicationConfig() {
        return null;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"file:${project.home}/conf/ucm-server.xml"};
    }


}
