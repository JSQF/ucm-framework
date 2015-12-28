package com.saike.ucm.web.form;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by huawei on 12/28/15.
 */
public class ShowUpdateProjectForm {

    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
