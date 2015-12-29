package com.saike.ucm.dao;

import com.saike.ucm.domain.Environment;
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

    Integer getMaxOrder();
}
