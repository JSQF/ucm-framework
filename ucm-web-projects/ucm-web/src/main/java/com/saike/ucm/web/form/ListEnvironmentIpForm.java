package com.saike.ucm.web.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by huawei on 12/30/15.
 */
public class ListEnvironmentIpForm extends DataTableForm{
    private String environmentId;

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
