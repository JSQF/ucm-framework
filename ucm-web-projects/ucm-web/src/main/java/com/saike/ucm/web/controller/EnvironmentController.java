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
import com.saike.ucm.web.form.*;
import com.saike.ucm.web.utils.Constants;
import com.saike.ucm.web.utils.DataTableUtils;
import com.saike.ucm.web.utils.FormCheckUtils;
import com.saike.ucm.web.utils.ResponseUtils;
import com.sun.tools.javac.comp.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
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
    private PlatformTransactionManager ptx;

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
    public void listEnvironmentAction(HttpServletResponse response, ListEnvironmentForm form) throws IOException, ServletException {
        DataTableResult<ListEnvironmentRecord> dataTableResult = new DataTableResult<>();
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

    @RequestMapping("/change-environment-status")
    public void changeEnvironmentStatusAction(HttpServletResponse response, ChangeEnvironmentStatusForm form) throws ServletException, IOException {
        try {
            FormCheckUtils.checkChangeEnvironmentStatusForm(form);
        } catch (IllegalParameterException e) {
            logger.error("", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }
        Environment environment = null;
        try {
            environment = this.environmentService.getEnvironmentById(form.getEnvironmentId());
        } catch (UcmServiceException e) {
            logger.error("获取环境信息异常", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, e.getMessage(), null).toJson());
            return;
        }

        if (environment == null) {
            logger.error("获取环境信息不存在");
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, "获取环境信息不存在", null).toJson());
            return;
        }

        try {
            this.environmentService.updateEnvironmentStatus(Integer.parseInt(form.getEnvironmentId()), form.getStatus());
        } catch (UcmServiceException e) {
            logger.error("更新环境状态异常", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }
        ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, null, null).toJson());

    }

    @RequestMapping("/down-environment")
    public void downEnvironmentAction(HttpServletResponse response, ChangeEnvironmentOrderForm form) throws ServletException, IOException {

        Environment currentEnvironment = null;
        Environment nextEnvironment = null;
        try {
            currentEnvironment= this.environmentService.getEnvironmentById(form.getEnvironmentId());
            nextEnvironment = this.environmentService.getEnvironmentByOrder(Integer.parseInt(form.getOrder()) +1);

            if (currentEnvironment == null|| nextEnvironment == null) {
                return;
            }


        } catch (UcmServiceException e) {
            e.printStackTrace();
        }

        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = ptx.getTransaction(definition);
        try{
            int order = nextEnvironment.getOrder();
            this.environmentService.updateEnvironmentOrder(currentEnvironment.getId(), order);
            this.environmentService.updateEnvironmentOrder(nextEnvironment.getId(), currentEnvironment.getOrder());
            ptx.commit(status);
        }catch (UcmServiceException e) {
            ptx.rollback(status);
        }
    }

    @RequestMapping("/up-environment")
    public void upEnvironmentAction(HttpServletResponse response, ChangeEnvironmentOrderForm form) throws ServletException, IOException {

    }
    @RequestMapping("/list-environment-ip")
    public void listEnvironmentIp(HttpServletResponse response, ListEnvironmentIpForm form) throws ServletException, IOException {

        try {
            FormCheckUtils.checkListEnvironmentIpForm(form);
        } catch (IllegalParameterException e) {
            logger.error("查询环境IP异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), e.getMessage());
            return;
        }

        DataTableResult<ListEnvironmentIpRecord> dataTableResult = new DataTableResult<>();

        try {
            List<EnvironmentIp> allEnvironmentIp = this.environmentService.getAllEnvironmentIp(form.getEnvironmentId());
            dataTableResult.setData(DataTableUtils.getListEnvironmentIpRecord(allEnvironmentIp));
            dataTableResult.setDraw(form.getDraw());
            dataTableResult.setRecordsTotal(allEnvironmentIp.size());
            dataTableResult.setRecordsFiltered(allEnvironmentIp.size());
            DataTableUtils.dataTableResponse(response, dataTableResult);
        } catch (UcmServiceException e) {
            logger.error("查询环境IP异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), e.getMessage());
        }
    }

    @RequestMapping("/add-environment-ip")
    public void addEnvironmentIpAction(HttpServletResponse response, AddEnvironmentIpForm form) throws ServletException, IOException {
        try {
            FormCheckUtils.checkAddEnvironmentIpForm(form);
        } catch (IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }
        try {
            Environment environment = this.environmentService.getEnvironmentById(form.getEnvironmentId());
            if (environment == null) {
                ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_NOT_FOUND, "未找到对应环境信息", null).toJson());
                return;
            }
            EnvironmentIp environmentIp = this.environmentService.getEnvironmentIp(form.getIp());
            if (environmentIp != null) {
                ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, "IP已存在" + this.environmentService.getEnvironmentById("" + environmentIp.getEnvironmentId()).getName() + "中", null).toJson());
                return;
            }
            this.environmentService.addEnvironmentIp(environment.getId(), form.getIp());
        } catch (UcmServiceException e) {
            logger.error("", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, e.getMessage(), null).toJson());
            return;
        }
        ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, null, null).toJson());

    }

    @RequestMapping("/delete-environment-ip")
    public void deleteEnvironmentIpAction(HttpServletResponse response, DeleteEnvironmentForm form) throws ServletException, IOException {

        try {
            FormCheckUtils.checkDeleteEnvironmentIpForm(form);
        } catch (IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }

        Integer id = Integer.parseInt(form.getEnvironmentIpId());
        try {
            EnvironmentIp environmentIp = this.environmentService.getEnvironmentIpById(id);
            if (environmentIp == null) {
                ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_NOT_FOUND, "没有找到对应IP信息", null).toJson());
                return;
            }
        } catch (UcmServiceException e) {
            logger.error("获取ip信息异常", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, "获取ip信息异常", null).toJson());
            return;
        }

        try {
            this.environmentService.deleteEnvironmentIpById(id);
        } catch (UcmServiceException e) {
            logger.error("删除IP失败", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, "删除失败", null).toJson());
            return;
        }

        ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, null, null).toJson());

    }
}
