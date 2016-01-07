package com.saike.ucm.service;

import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.ProjectConfigurationVersionControl;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;

import java.util.Map;

/**
 * Created by huawei on 12/28/15.
 */
public interface ProjectService {

    void addProject(String code, String name, String type, String desc) throws AlreadyExistsException, UcmServiceException;

    Map<String, String> getAllProjectCodeNameMap();

    PaginationResult<Project> paginate(String projectCode, String projectType, int start, int length) throws UcmServiceException;

    Project getProjectById(String projectId) throws UcmServiceException;

    Project getProjectByCode(String projectCode) throws UcmServiceException;

    ProjectConfigurationVersionControl getProjectConfigurationVersionControl(String projectCode, String version) throws UcmServiceException;
}
