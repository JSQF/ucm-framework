package com.saike.ucm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huawei on 12/23/15.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping("/index")
    public String indexAction() {
        return "project/index";
    }
}
