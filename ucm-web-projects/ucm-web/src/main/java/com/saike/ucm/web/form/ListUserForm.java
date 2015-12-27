package com.saike.ucm.web.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by huawei on 12/26/15.
 */
public class ListUserForm extends DataTableForm {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
