package com.saike.ucm.service.impl;

import com.saike.ucm.dao.ProjectDAO;
import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.dao.Pagination;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.ProjectService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huawei on 12/28/15.
 */

public class DefaultProjectService implements ProjectService {

    private ProjectDAO projectDAO;

    @Override
    public void addProject(String code, String name, String type, String desc) throws AlreadyExistsException, UcmServiceException {

        Project project;

        try{
            project = this.projectDAO.getProjectByCode(code);
        }catch (Exception e) {
            throw new UcmServiceException("查询项目代码异常", e);
        }

        if (project != null) {
            throw new AlreadyExistsException("项目代码" + code + "已经存在");
        }

        project = new Project();
        project.setCode(code);
        project.setName(name);
        project.setType(type);
        project.setDescription(desc);
        Date date = new Date();
        project.setCreateTime(date);
        project.setUpdateTime(date);

        try{
            this.projectDAO.saveProject(project);
        }catch(Exception e) {
            throw new UcmServiceException("保存项目异常", e);
        }
    }

    @Override
    public Map<String, String> getAllProjectCodeNameMap(){
        List<Project> projects = null;
        try{
            projects = this.projectDAO.getAllProjects();
        }catch (Exception e) {

        }
        Map<String,String> map = new HashMap<>();
        if (projects == null) {
            return map;
        }
        for(Project project: projects) {
            map.put(project.getCode(), project.getName());
        }
        return  map;
    }

    @Override
    public PaginationResult<Project> paginate(String projectCode, String projectType, int start, int length) throws UcmServiceException {Pagination<Project> condition = new Pagination<>();
        Project queryProject = new Project();
        queryProject.setCode(projectCode);
        queryProject.setType(projectType);
        condition.setCondition(queryProject);
        condition.setStart(start);
        condition.setLength(length);

        PaginationResult<Project> paginationResult = new PaginationResult<>();
        try{
            List<Project> projects = this.projectDAO.paginate(condition);
            int count = this.projectDAO.paginateCount(condition);
            paginationResult.setResults(projects);
            paginationResult.setCount(count);
        }catch (Exception e) {
            throw new UcmServiceException("查询项目列表异常", e);
        }
        return paginationResult;
    }


    public ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
}
