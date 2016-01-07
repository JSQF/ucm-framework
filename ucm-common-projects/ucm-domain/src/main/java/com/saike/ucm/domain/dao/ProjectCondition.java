package com.saike.ucm.domain.dao;

/**
 * Created by huawei on 1/7/16.
 */
public class ProjectCondition {

    private Integer id;
    private boolean projectNameLike = false;
    private String projectName;
    private boolean projectCodeLike = false;
    private String projectCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isProjectNameLike() {
        return projectNameLike;
    }

    public void setProjectNameLike(boolean projectNameLike) {
        this.projectNameLike = projectNameLike;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isProjectCodeLike() {
        return projectCodeLike;
    }

    public void setProjectCodeLike(boolean projectCodeLike) {
        this.projectCodeLike = projectCodeLike;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
