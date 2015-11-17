package com.saike.ucm.venus.impl;

import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.backend.context.RequestContext;
import com.saike.ucm.api.UcmApiService;
import com.saike.ucm.domain.api.UcmProperty;
import com.saike.ucm.exception.api.UcmApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huawei on 11/17/15.
 */
public class DefaultUcmApiService implements UcmApiService {

    private String zookeeperServerList;

    public DefaultUcmApiService() {
        Watcher watcher = new Watcher();
        watcher.start();
    }

    @Override
    public List<UcmProperty> getProperties(@Param(name = "projectCode") String projectCode, @Param(name = "version") String version) throws UcmApiException {
        return getPropertiesInternal(RequestContext.getRequestContext().getRequestInfo().getRemoteIp(), projectCode, version);
    }

    @Override
    public List<UcmProperty> getProperties(@Param(name = "host") String host, @Param(name = "projectCode") String projectCode, @Param(name = "version") String version) throws UcmApiException {
        String zookeeperHost = RequestContext.getRequestContext().getRequestInfo().getRemoteIp();
        if (validateZookeeperHost(zookeeperHost)) {
            return getPropertiesInternal(host, projectCode, version);
        }
        return new ArrayList<UcmProperty>();
    }

    private List<UcmProperty> getPropertiesInternal(String host, String projectCode, String version) {

        return null;
    }

    private boolean validateZookeeperHost(String zookeeperHost) {

        return false;
    }

    public String getZookeeperServerList() {
        return zookeeperServerList;
    }

    public void setZookeeperServerList(String zookeeperServerList) {
        this.zookeeperServerList = zookeeperServerList;
    }

    static class Watcher extends Thread {

        public void run() {

        }

    }
}
