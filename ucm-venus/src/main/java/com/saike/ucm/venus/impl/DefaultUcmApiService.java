package com.saike.ucm.venus.impl;

import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.backend.context.RequestContext;
import com.saike.ucm.api.UcmApiService;
import com.saike.ucm.domain.api.UcmProperty;
import com.saike.ucm.exception.api.UcmApiException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by huawei on 11/17/15.
 */
@Component(value = "ucmApiService")
public class DefaultUcmApiService implements UcmApiService {

    private String zookeeperServerList;

    public DefaultUcmApiService() {
        Watcher watcher = new Watcher();
        //watcher.start();
    }

    @Override
    public List<UcmProperty> getProperties(@Param(name = "projectCode") String projectCode, @Param(name = "version") String version) throws UcmApiException {
        //ucm-web mock data
        List<UcmProperty> lists = new ArrayList<>();

        InputStream is = null;

        try{
            is = new FileInputStream(new File("/Users/huawei/ucm-web.properties"));
            Properties props = new Properties();
            props.load(is);

            Enumeration<Object> enumeration = props.keys();
            while(enumeration.hasMoreElements()) {
                Object key = enumeration.nextElement();
                UcmProperty property = new UcmProperty();
                property.setName(key.toString());
                property.setValue(props.get(key).toString());
                property.setDisplay(true);
                property.setUpdateTime(new Date());
                lists.add(property);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try{
                    is.close();
                }catch (Exception e){

                }
            }
        }


        return lists;
        //return getPropertiesInternal(RequestContext.getRequestContext().getRequestInfo().getRemoteIp(), projectCode, version);
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

            while(true){
                System.out.println("run");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {

                }
            }

        }

    }
}
