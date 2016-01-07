package com.saike.ucm.service.impl;

import com.saike.ucm.dao.EnvironmentDAO;
import com.saike.ucm.domain.Environment;
import com.saike.ucm.domain.EnvironmentIp;
import com.saike.ucm.exception.UcmException;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.EnvironmentService;
import com.sun.tools.javac.comp.Env;

import java.util.Date;
import java.util.List;

/**
 * Created by huawei on 12/29/15.
 */
public class DefaultEnvironmentService  implements EnvironmentService {

    private EnvironmentDAO environmentDAO;

    @Override
    public List<Environment> getAllEnvironment() throws UcmServiceException {
        try{
            return this.environmentDAO.getAllEnvironment();
        }catch(Exception e) {
            throw new UcmServiceException("获取所有环境信息异常", e);
        }
    }

    @Override
    public void addEnvironment(String code, String name) throws AlreadyExistsException, UcmException {
        Environment environment = null;
        try{
            environment = this.environmentDAO.getEnvironmentByCode(code);
        }catch(Exception e) {
            throw new UcmServiceException("查询环境信息异常", e);
        }
        if (environment != null) {
            throw new AlreadyExistsException(code + "环境已经存在, 名称为:" + environment.getName());
        }

        try{
            Integer order = this.environmentDAO.getMaxOrder();

            if (order == null) {
                order = 1;
            }else {
                order = order + 1;
            }

            environment = new Environment();
            environment.setName(name);
            environment.setCode(code);
            environment.setOrder(order);
            Date date = new Date();
            environment.setCreateTime(date);
            environment.setUpdateTime(date);

            this.environmentDAO.addEnvironment(environment);
        }catch (Exception e) {
            throw new UcmServiceException("保存环境信息异常", e);
        }


    }

    @Override
    public List<EnvironmentIp> getAllEnvironmentIp(String environmentId) throws UcmServiceException{
        try{
            return this.environmentDAO.getAllEnvironmentIpByEnvironmentId(environmentId);
        }catch (Exception e) {
            throw new UcmServiceException("查询环境ip异常", e);
        }
    }

    @Override
    public Environment getEnvironmentById(String environmentId) throws UcmServiceException {
        try{
            return this.environmentDAO.getEnvironmentById(Integer.parseInt(environmentId));
        }catch (Exception e) {
            throw new UcmServiceException("查询环境信息异常", e);
        }
    }

    @Override
    public void addEnvironmentIp(Integer id, String ip) throws UcmServiceException {
        try{
            EnvironmentIp environmentIp = new EnvironmentIp();
            environmentIp.setEnvironmentId(id);
            environmentIp.setIp(ip);
            this.environmentDAO.addEnvironmentIp(environmentIp);
        }catch (Exception e) {
            throw new UcmServiceException("保存环境IP异常", e);
        }
    }

    @Override
    public EnvironmentIp getEnvironmentIp(String ip) throws UcmServiceException {
        try{
            return this.environmentDAO.getEnvironmentIp(ip);
        }catch (Exception e) {
            throw new UcmServiceException("获取环境IP信息异常", e);
        }
    }

    @Override
    public EnvironmentIp getEnvironmentIpById(int id) throws UcmServiceException {
        try{
            return this.environmentDAO.getEnvironmentIpById(id);
        }catch (Exception e) {
            throw new UcmServiceException("获取IP信息异常", e);
        }
    }

    @Override
    public void deleteEnvironmentIpById(Integer id) throws UcmServiceException {
        try{
            this.environmentDAO.deleteEnvironmentIpById(id);
        }catch (Exception e) {
            throw new UcmServiceException(e);
        }
    }

    @Override
    public void updateEnvironmentStatus(Integer environmentId, String status) throws UcmServiceException {
        Environment environment = new Environment();
        environment.setId(environmentId);
        environment.setActive("0".equals(status) ? false : true);
        try{
            this.environmentDAO.updateEnvironment(environment);
        }catch (Exception e) {
            throw new UcmServiceException(e);
        }
    }

    @Override
    public Environment getEnvironmentByOrder(int order) throws UcmServiceException {
        return this.environmentDAO.getEnvironmentByOrder(order);
    }

    @Override
    public void updateEnvironmentOrder(Integer id, int order) throws UcmServiceException {
        Environment environment = new Environment();
        environment.setId(id);
        environment.setOrder(order);

        try{
            this.environmentDAO.updateEnvironment(environment);
        }catch (Exception e) {
            throw new UcmServiceException(e);
        }

    }

    @Override
    public Environment getEnvironmentByIp(String clientIp) throws UcmServiceException {
        if(clientIp == null) {
            return null;
        }
        String[] ipArray = clientIp.split("\\.");
        int length = ipArray.length;

        EnvironmentIp environmentIp = null;

        Environment environment = null;

        try{
            do{
                StringBuffer ip = new StringBuffer();
                for(int i = 0;i < length -1; i++) {
                    ip.append(ipArray[i] + ".");
                }
                ip.append(ipArray[length - 1]);
                environmentIp = this.environmentDAO.getEnvironmentIp(ip.toString());
                length = length - 1;
            }while(environmentIp == null && length != 0);



            if (environmentIp == null) {
                environment = this.environmentDAO.getDefaultEnvironment();
            }else {
                environment = this.environmentDAO.getEnvironmentById(environmentIp.getEnvironmentId());
            }
        }catch (Exception e) {
            throw new UcmServiceException(e);
        }

        if (environment == null) {
            throw new UcmServiceException("未找到对应环境");
        }

        return environment;
    }

    public EnvironmentDAO getEnvironmentDAO() {
        return environmentDAO;
    }

    public void setEnvironmentDAO(EnvironmentDAO environmentDAO) {
        this.environmentDAO = environmentDAO;
    }
}
