package com.saike.ucm.web.utils;

import com.saike.ucm.exception.IllegalParameterException;
import com.saike.ucm.web.form.*;
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

    public static void checkListEnvironmentIpForm(ListEnvironmentIpForm form) throws IllegalParameterException {
        if(!StringUtils.hasLength(form.getEnvironmentId())) {
            throw new IllegalParameterException("环境ID不能为空");
        }

        try{
            Integer.parseInt(form.getEnvironmentId());
        }catch (NumberFormatException e) {
            throw new IllegalParameterException("环境ID格式不正确", e);
        }
    }

    public static void checkAddEnvironmentIpForm(AddEnvironmentIpForm form) throws IllegalParameterException {
        if(!StringUtils.hasLength(form.getIp())) {
            throw new IllegalParameterException("环境ID不存在");
        }

        if(!StringUtils.hasLength(form.getIp())){
            throw new IllegalParameterException("IP不能为空");
        }

        String ip = form.getIp();

        if (ip.endsWith("\\" +Constants.DOT)) {
            throw new IllegalParameterException("IP格式不正确");
        }

        String[] array = ip.split("\\" +Constants.DOT);

        if (array == null || array.length <= 0) {
            throw new IllegalParameterException("IP格式不正确");
        }

        for(int i = 0; i < array.length; i++) {
            try{
                int data = Integer.parseInt(array[i]);

                if (data < 0 || data > 255) {
                    throw new IllegalParameterException("IP格式不正确");
                }
            }catch (NumberFormatException e) {
                throw new IllegalParameterException("IP格式不正确");
            }
        }


    }
}
