package com.saike.ucm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huawei on 11/17/15.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String indexAction(){
        return "index";
    }

}
