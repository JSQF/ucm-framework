package com.saike.ucm.web.controller;

import com.saike.ucm.domain.Environment;
import com.saike.ucm.domain.EnvironmentIp;
import com.saike.ucm.exception.IllegalParameterException;
import com.saike.ucm.exception.UcmException;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.EnvironmentService;
import com.saike.ucm.web.domain.DataTableResult;
import com.saike.ucm.web.domain.ListEnvironmentIpRecord;
import com.saike.ucm.web.domain.ListEnvironmentRecord;
import com.saike.ucm.web.form.AddEnvironmentForm;
import com.saike.ucm.web.form.ListEnvironmentForm;
import com.saike.ucm.web.form.ListEnvironmentIpForm;
import com.saike.ucm.web.utils.Constants;
import com.saike.ucm.web.utils.DataTableUtils;
import com.saike.ucm.web.utils.FormCheckUtils;
import com.saike.ucm.web.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
@Controller
@RequestMapping("/env")
public class EnvironmentController {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentController.class);

    @Resource
    private EnvironmentService environmentService;

    @RequestMapping("/show-manager")
    public String showEnvironmentManagerAction() {
        return "env/manager";
    }

    @RequestMapping("/add-environment")
    public void addEnvironmentAction(HttpServletResponse response, AddEnvironmentForm form) throws ServletException, IOException {

        try {
            FormCheckUtils.checkAddEnvironmentForm(form);
            this.environmentService.addEnvironment(form.getCode(), form.getName());
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, null, null).toJson());
        } catch (IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
        } catch (AlreadyExistsException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
        } catch (UcmException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, e.getMessage(), null).toJson());
        }


    }

    @RequestMapping("/list-environment")
    public void listEnvironmentAction(HttpServletResponse response, ListEnvironmentForm form) throws IOException, ServletException{
        DataTableResult<ListEnvironmentRecord> dataTableResult  = new DataTableResult<>();
        try {
            List<Environment> allEnvironmentList = this.environmentService.getAllEnvironment();
            dataTableResult.setData(DataTableUtils.getListEnvironmentRecord(allEnvironmentList));
            dataTableResult.setDraw(form.getDraw());
            dataTableResult.setRecordsFiltered(allEnvironmentList.size());
            dataTableResult.setRecordsTotal(allEnvironmentList.size());
            DataTableUtils.dataTableResponse(response, dataTableResult);
        } catch (UcmServiceException e) {
            logger.error("查询环境异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), e.getMessage());
        }
    }

    @RequestMapping("/list-environment-ip")
    public void listEnvironmentIp(HttpServletResponse response, ListEnvironmentIpForm form) throws ServletException, IOException {

        try{
            FormCheckUtils.checkListEnvironmentIpForm(form);
        }catch(IllegalParameterException e) {
            logger.error("查询环境IP异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), e.getMessage());
            return;
        }

        DataTableResult<ListEnvironmentIpRecord> dataTableResult  = new DataTableResult<>();

        try{
            List<EnvironmentIp> allEnvironmentIp = this.environmentService.getAllEnvironmentIp(form.getEnvironmentId());
            dataTableResult.setData(DataTableUtils.getListEnvironmentIpRecord(allEnvironmentIp));
            dataTableResult.setDraw(form.getDraw());
            dataTableResult.setRecordsTotal(allEnvironmentIp.size());
            dataTableResult.setRecordsFiltered(allEnvironmentIp.size());
            DataTableUtils.dataTableResponse(response, dataTableResult);
        }catch (UcmServiceException e) {
            logger.error("查询环境IP异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), e.getMessage());
        }

    }
}
