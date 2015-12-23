package com.saike.ucm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huawei on 12/18/15.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/index")
    public String showLoginAction(){
        return "login";
    }





}
