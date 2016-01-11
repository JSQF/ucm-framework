package com.saike.ucm.venus.impl;

import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.backend.context.RequestContext;
import com.saike.ucm.api.UcmApiService;
import com.saike.ucm.domain.Environment;
import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.ConfigurationVersionControl;
import com.saike.ucm.domain.api.UcmProperty;
import com.saike.ucm.exception.api.UcmApiException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.EnvironmentService;
import com.saike.ucm.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by huawei on 11/17/15.
 */
@Component(value = "ucmApiService")
public class DefaultUcmApiService implements UcmApiService {

    private static Logger logger = LoggerFactory.getLogger(DefaultUcmApiService.class);

    @Autowired(required = true)
    private EnvironmentService environmentService;

    @Resource
    private ProjectService projectService;

    public DefaultUcmApiService() {
    }

    @Override
    public List<UcmProperty> getProperties(@Param(name = "projectCode") String projectCode, @Param(name = "version") String version) throws UcmApiException {
        String clientIp = RequestContext.getRequestContext().getRequestInfo().getRemoteIp();
        Environment environment = null;
        try {
            environment = this.environmentService.getEnvironmentByIp(clientIp);
        } catch (UcmServiceException e) {
            logger.error("获取环境信息异常", e);
            return new ArrayList<>();
        }
        if (environment == null) {
            return new ArrayList<>();
        }
        if (!environment.isActive()) {
            return new ArrayList<>();
        }
        Project project = null;
        try {
            project = this.projectService.getProjectByCode(projectCode);
        } catch (UcmServiceException e) {
            return new ArrayList<>();
        }
        if (project == null) {
            return new ArrayList<>();
        }
        if (!project.isActive()){
            return new ArrayList<>();
        }
        ConfigurationVersionControl pcvc = null;
        try{
            pcvc = this.projectService.getProjectConfigurationVersionControl(projectCode, version);
        }catch (UcmServiceException e) {
            return new ArrayList<>();
        }
        if (pcvc == null) {
            return new ArrayList<>();
        }
        if (!pcvc.isActive()) {
            return new ArrayList<>();
        }
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

}
