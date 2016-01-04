package com.saike.ucm.dao;

import com.saike.ucm.domain.Environment;
import com.saike.ucm.domain.EnvironmentIp;
import com.saike.ucm.domain.dao.Pagination;

import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
public interface EnvironmentDAO {

    void addEnvironment(Environment environment);

    Environment getDefaultEnvironment();

    List<Environment> getAllEnvironment();

    Environment getEnvironmentByCode(String code);

    Environment getEnvironmentById(Integer id);

    Integer getMaxOrder();

    List<EnvironmentIp> getAllEnvironmentIpByEnvironmentId(String environmentId);

    void addEnvironmentIp(EnvironmentIp environmentIp);

    EnvironmentIp getEnvironmentIp(String ip);

    EnvironmentIp getEnvironmentIpById(int id);

    void deleteEnvironmentIpById(Integer id);

    void updateEnvironment(Environment environment);

    Environment getEnvironmentByOrder(int order);
}
