package com.saike.ucm.service;

import com.saike.ucm.domain.Environment;
import com.saike.ucm.domain.EnvironmentIp;
import com.saike.ucm.exception.UcmException;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;

import java.util.List;

/**
 * Created by huawei on 12/29/15.
 */
public interface EnvironmentService {

    List<Environment> getAllEnvironment() throws UcmServiceException;

    void addEnvironment(String code, String name) throws AlreadyExistsException, UcmException;

    List<EnvironmentIp> getAllEnvironmentIp(String environmentId) throws UcmServiceException;

    Environment getEnvironmentById(String environmentId) throws UcmServiceException;

    void addEnvironmentIp(Integer id, String ip) throws UcmServiceException;

    EnvironmentIp getEnvironmentIp(String ip) throws UcmServiceException;

    EnvironmentIp getEnvironmentIpById(int id) throws UcmServiceException;

    void deleteEnvironmentIpById(Integer id) throws UcmServiceException;

    void updateEnvironmentStatus(Integer id, String status) throws UcmServiceException;

    Environment getEnvironmentByOrder(int order) throws UcmServiceException;

    void updateEnvironmentOrder(Integer id, int order) throws UcmServiceException;

    Environment getEnvironmentByIp(String clientIp) throws UcmServiceException;
}
