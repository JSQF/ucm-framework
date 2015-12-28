package com.saike.ucm.dao;

import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.dao.Pagination;

import java.util.List;

/**
 * Created by huawei on 12/28/15.
 */
public interface ProjectDAO {

    Project getProjectByCode(String projectCode);

    void saveProject(Project project);

    List<Project> getAllProjects();

    List<Project> paginate(Pagination<Project> condition);

    int paginateCount(Pagination condition);
}
