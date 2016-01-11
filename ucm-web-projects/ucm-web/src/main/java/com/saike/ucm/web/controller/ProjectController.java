package com.saike.ucm.web.controller;

import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.ProjectType;
import com.saike.ucm.exception.IllegalParameterException;
import com.saike.ucm.exception.service.AlreadyExistsException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.ProjectService;
import com.saike.ucm.web.domain.DataTableResult;
import com.saike.ucm.web.domain.ListProjectRecord;
import com.saike.ucm.web.form.AddProjectForm;
import com.saike.ucm.web.form.ListProjectForm;
import com.saike.ucm.web.form.ShowUpdateProjectForm;
import com.saike.ucm.web.form.UpdateProjectForm;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huawei on 12/23/15.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private ProjectService projectService;

    @RequestMapping("/show-add")
    public String showAddAction(HttpServletRequest request) {
        request.setAttribute("projectTypeMap", ProjectType.getNormalProjectTypeMap());
        return "project/add";
    }

    @RequestMapping("/show-manager")
    public String showManagerAction(HttpServletRequest request){
        request.setAttribute("projectTypeMap", ProjectType.getNormalProjectTypeMap());
        request.setAttribute("projectCodeNameMap", this.projectService.getAllProjectCodeNameMap());
        return "project/manager";
    }

    @RequestMapping("/add-project")
    public void addProjectAction(HttpServletResponse response, AddProjectForm form) throws ServletException, IOException {
        try{
            FormCheckUtils.checkAddProjectForm(form);
            this.projectService.addProject(form.getProjectCode(), form.getProjectName(), form.getProjectType(), form.getProjectDesc());
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, "添加成功", null).toJson());
        }catch(IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }catch(AlreadyExistsException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }catch (UcmServiceException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, e.getMessage(), null).toJson());
            return;
        }
    }

    @RequestMapping("/list-project")
    public void listProjectAction(HttpServletResponse response, ListProjectForm form) throws ServletException, IOException {
        try{
            PaginationResult<Project> paginateResult = this.projectService.paginate(form.getProjectCode(), form.getProjectType(), form.getStart(), form.getLength());
            DataTableResult<ListProjectRecord>  dtr = new DataTableResult<>();
            dtr.setDraw(form.getDraw());
            dtr.setData(DataTableUtils.getListProjectRecord(paginateResult.getResults()));
            dtr.setRecordsFiltered(paginateResult.getCount());
            dtr.setRecordsTotal(paginateResult.getCount());
            DataTableUtils.dataTableResponse(response, dtr);
        }catch(UcmServiceException e) {
            logger.error("查询项目异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), "查询项目异常: " + e.getMessage());

        }
    }

    @RequestMapping("/get-project-by-id")
    public void showUpdateProjectAction(HttpServletResponse response, ShowUpdateProjectForm form) throws ServletException, IOException {
        try{
            FormCheckUtils.checkShowUpdateProjectForm(form);
            Project project = this.projectService.getProjectById(form.getProjectId());
            if (project == null) {
                ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_NOT_FOUND, "未找到对应项目", null).toJson());
                return;
            }
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_OK, null, project).toJson());
        } catch (IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
        }catch(UcmServiceException e) {
            logger.error("", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, e.getMessage(), null).toJson());
        }
    }


    @RequestMapping("/update-project")
    public void updateProjectAction(HttpServletResponse response, UpdateProjectForm form) throws ServletException, IOException {
        try{
            FormCheckUtils.checkUpdateProjectForm(form);
        }catch (IllegalParameterException e) {
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_BAD_REQUEST, e.getMessage(), null).toJson());
            return;
        }

        try{
            Project project = this.projectService.getProjectById(form.getProjectId());
            if (project == null) {
                ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_NOT_FOUND, "未找到对应项目", null).toJson());
                return;
            }
            this.projectService.updateProject(project, form.getName(), form.getDescription(), form.getType(), form.getStatus());
        }catch(UcmServiceException e) {
            logger.error("更新项目异常", e);
            ResponseUtils.responseJson(response, ResponseUtils.getAjaxResponse(Constants.UCM_WEB_CODE_INTERNAL_ERROR, "更新项目异常", null).toJson());
            return;
        }
    }
}
