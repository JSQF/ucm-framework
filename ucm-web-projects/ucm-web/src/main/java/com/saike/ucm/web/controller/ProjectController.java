package com.saike.ucm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huawei on 12/23/15.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping("/show-add")
    public String showAddAction() {
        return "project/add";
    }

    @RequestMapping("/show-manager")
    public String showManagerAction(){
        return "project/manager";
    }
}
