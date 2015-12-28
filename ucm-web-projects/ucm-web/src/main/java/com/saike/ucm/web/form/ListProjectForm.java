package com.saike.ucm.web.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by huawei on 12/28/15.
 */
public class ListProjectForm extends DataTableForm{

    private String projectCode;
    private String projectType;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
