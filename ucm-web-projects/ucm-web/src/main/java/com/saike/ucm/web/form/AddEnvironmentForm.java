package com.saike.ucm.web.form;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by huawei on 12/29/15.
 */
public class AddEnvironmentForm {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
