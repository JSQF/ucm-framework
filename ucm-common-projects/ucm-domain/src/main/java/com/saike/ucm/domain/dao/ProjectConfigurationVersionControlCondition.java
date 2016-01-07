package com.saike.ucm.domain.dao;

/**
 * Created by huawei on 1/7/16.
 */
public class ProjectConfigurationVersionControlCondition {

    private Integer id;
    private Integer projectId;
    private boolean versionLike = false;
    private String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public boolean isVersionLike() {
        return versionLike;
    }

    public void setVersionLike(boolean versionLike) {
        this.versionLike = versionLike;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
