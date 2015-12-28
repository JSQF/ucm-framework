package com.saike.ucm.web.domain;

import com.meidusa.fastjson.JSON;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by huawei on 12/28/15.
 */
public class AjaxResponse<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
