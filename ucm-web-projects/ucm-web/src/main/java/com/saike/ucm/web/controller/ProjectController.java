package com.saike.ucm.web.controller;

import com.saike.ucm.domain.ProjectType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huawei on 12/23/15.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping("/show-add")
    public String showAddAction(HttpServletRequest request) {
        request.setAttribute("projectTypeMap", ProjectType.getProjectTypeMap());
        return "project/add";
    }

    @RequestMapping("/show-manager")
    public String showManagerAction(){
        return "project/manager";
    }
}
