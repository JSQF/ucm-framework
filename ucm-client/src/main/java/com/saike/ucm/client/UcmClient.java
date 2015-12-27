package com.saike.ucm.client;

import com.meidusa.venus.client.simple.SimpleServiceFactory;
import com.saike.ucm.api.UcmApiService;
import com.saike.ucm.client.context.UcmContextHolder;
import com.saike.ucm.client.domain.UcmServer;
import com.saike.ucm.client.exception.ClientException;
import com.saike.ucm.domain.api.UcmProperty;
import com.saike.ucm.exception.api.ConfigNotInUseException;
import com.saike.ucm.exception.api.ProjectNotInUseException;
import com.saike.ucm.exception.api.UcmApiException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by huawei on 11/17/15.
 */
public class UcmClient {

    private static Logger logger = LoggerFactory.getLogger(UcmClient.class);

    private static Logger propertyLogger = LoggerFactory.getLogger("ucm.property.logger");

    private File propertyConfigFile;
    private boolean isSync = false;

    public UcmClient(String propertyConfigLocation) throws ClientException {
        try {
            propertyConfigFile = ResourceUtils.getFile(propertyConfigLocation);
        } catch (FileNotFoundException e) {
            throw new ClientException("文件不存在", e);
        }
    }

    public void load() throws ClientException {

        Properties localProperties = loadLocalProperties();

        String ipAddressList = getUcmServerIpAddressList(localProperties.getProperty("ucm.ipAddressList"), System.getProperty("global.ucm.ipAddressList"));
        String projectCode = localProperties.getProperty("ucm.projectCode");
        String projectVersion = localProperties.getProperty("ucm.projectVersion");

        List<UcmServer> ucmServerList = parserUcmServer(ipAddressList);

        if (ucmServerList == null || ucmServerList.size() == 0) {
            throw new ClientException("ucm服务器信息不能为空");
        }

        for (UcmServer ucmServer : ucmServerList) {
            if (logger.isDebugEnabled()) {
                logger.debug("连接ucm服务 - ip: {}, port: {}", ucmServer.getHost(), ucmServer.getPort());
            }
            SimpleServiceFactory ssf = new SimpleServiceFactory(ucmServer.getHost(), ucmServer.getPort());

            UcmApiService ucmApiService = ssf.getService(UcmApiService.class);
            List<UcmProperty> propertyList = null;

            try {
                propertyList = ucmApiService.getProperties(projectCode, projectVersion);
            } catch (ProjectNotInUseException e) {
                logger.error("ucm项目不可用", e);
                break;
            } catch (ConfigNotInUseException e) {
                logger.error("ucm项目配置不可用", e);
                break;
            } catch (UcmApiException e) {
                logger.error("获取ucm配置异常");
            }

            if (propertyList != null && propertyList.size() > 0) {
                propertyLogger.info("--------------------------------------------------------------------------------");
                for (UcmProperty property : propertyList) {
                    System.setProperty(property.getName(), property.getValue());
                    UcmContextHolder.getHolder().put(property.getName(), property.getValue());
                    StringBuilder builder = new StringBuilder(property.getName() + ", ");
                    if (property.isDisplay()) {
                        builder.append(property.getValue());
                    } else {
                        builder.append("********");
                    }
                    propertyLogger.info(builder.toString());
                }
                propertyLogger.info("--------------------------------------------------------------------------------");
                break;
            }

            Enumeration<Object> propsIterator = localProperties.keys();

        }
    }

    private Properties loadLocalProperties() throws ClientException {
        InputStream is = null;
        Properties localProperties = new Properties();
        try {
            is = new BufferedInputStream(new FileInputStream(propertyConfigFile));
            localProperties.load(is);
        } catch (IOException e) {
            throw new ClientException("", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return localProperties;
    }

    private String getUcmServerIpAddressList(String ipAddressList, String systemDefault) {
        if (ipAddressList == null) {
            return systemDefault;
        }
        return ipAddressList;
    }

    private List<UcmServer> parserUcmServer(String ipAddressList) {

        String[] ipAddressArray = ipAddressList.split(",");

        if (ipAddressArray == null || ipAddressArray.length <= 0) {
            return null;
        }

        List<UcmServer> ucmServerList = new ArrayList<UcmServer>();
        for (String ipAddress : ipAddressArray) {
            String[] array = ipAddress.split(":");
            if (array == null || array.length != 2) {
                continue;
            }
            String host = array[0];
            int port = -1;
            try {
                port = Integer.parseInt(array[1]);
            } catch (NumberFormatException nfe) {
                continue;
            }
            UcmServer ucmServer = new UcmServer();
            ucmServer.setHost(host);
            ucmServer.setPort(port);
            ucmServerList.add(ucmServer);
        }

        return ucmServerList;
    }
}
