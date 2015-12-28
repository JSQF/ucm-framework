package com.saike.ucm.web.form;

/**
 * Created by huawei on 12/28/15.
 */
public class UpdateProjectForm {

    private String projectId;
    private String type;
    private String description;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
