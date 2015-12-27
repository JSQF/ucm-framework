package com.saike.ucm.web.controller;

import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.User;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.UserService;
import com.saike.ucm.web.domain.DataTableResult;
import com.saike.ucm.web.domain.ListUserRecord;
import com.saike.ucm.web.form.ListUserForm;
import com.saike.ucm.web.utils.DataTableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huawei on 12/26/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/manager")
    public String showManagerAction(){
        return "user/manager";
    }

    @RequestMapping("/list-user")
    public void listUserAction(HttpServletResponse response, ListUserForm form) throws ServletException, IOException {
        try {
            PaginationResult<User> paginateResult = userService.paginate(form.getUsername(), form.getStart(), form.getLength());
            DataTableResult<ListUserRecord> dtr = new DataTableResult<>();
            dtr.setData(DataTableUtils.getListUserRecord(paginateResult.getResults()));
            dtr.setDraw(form.getDraw());
            dtr.setRecordsFiltered(paginateResult.getCount());
            dtr.setRecordsTotal(paginateResult.getCount());
            DataTableUtils.dataTableResponse(response, dtr);
        } catch (UcmServiceException e) {
            logger.error("查询用户异常", e);
            DataTableUtils.dataTableErrorResponse(response, form.getDraw(), "查询用户异常: " + e.getMessage());
        }
    }
}
