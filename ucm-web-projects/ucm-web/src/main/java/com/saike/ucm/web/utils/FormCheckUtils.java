package com.saike.ucm.web.utils;

import com.saike.ucm.exception.IllegalParameterException;
import com.saike.ucm.web.form.AddEnvironmentForm;
import com.saike.ucm.web.form.AddProjectForm;
import com.saike.ucm.web.form.ShowUpdateProjectForm;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.util.StringUtils;

/**
 * Created by huawei on 12/28/15.
 */
public class FormCheckUtils {

    public static void checkAddProjectForm(AddProjectForm form) throws IllegalParameterException {
        if (!StringUtils.hasLength(form.getProjectCode())) {
            throw new IllegalParameterException("请填写项目代码");
        }

        if (!StringUtils.hasLength(form.getProjectName())) {
            throw new IllegalParameterException("请填写项目名称");
        }

        if (!StringUtils.hasLength(form.getProjectType())) {
            throw new IllegalParameterException("请选择项目类型");
        }

        if(!StringUtils.hasLength(form.getProjectDesc())) {
            throw new IllegalParameterException("请填写项目描述");
        }
    }

    public static void checkShowUpdateProjectForm(ShowUpdateProjectForm form) throws IllegalParameterException {

        if(!StringUtils.hasLength(form.getProjectId())) {
            throw new IllegalParameterException("项目ID不能为空");
        }

        try{
            Integer.parseInt(form.getProjectId());
        }catch (NumberFormatException e) {
            throw new IllegalParameterException("项目ID格式不正确", e);
        }
    }

    public static void checkAddEnvironmentForm(AddEnvironmentForm form) throws IllegalParameterException{
        if (!StringUtils.hasLength(form.getName())) {
            throw new IllegalParameterException("请填写环境名称");
        }

        if(!StringUtils.hasLength(form.getCode())) {
            throw new IllegalParameterException("请填写环境代码");
        }
    }
}
