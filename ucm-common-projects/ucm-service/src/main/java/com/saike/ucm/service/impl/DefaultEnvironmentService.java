package com.saike.ucm.service.impl;

import com.saike.ucm.dao.EnvironmentDAO;
import com.saike.ucm.domain.Environment;
import com.saike.ucm.exception.UcmException;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.EnvironmentService;

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

    public EnvironmentDAO getEnvironmentDAO() {
        return environmentDAO;
    }

    public void setEnvironmentDAO(EnvironmentDAO environmentDAO) {
        this.environmentDAO = environmentDAO;
    }
}
