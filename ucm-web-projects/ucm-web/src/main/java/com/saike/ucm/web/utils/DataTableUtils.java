package com.saike.ucm.web.utils;

import com.meidusa.fastjson.JSON;
import com.saike.ucm.domain.Project;
import com.saike.ucm.domain.ProjectType;
import com.saike.ucm.domain.User;
import com.saike.ucm.web.domain.DataTableResult;
import com.saike.ucm.web.domain.ListProjectRecord;
import com.saike.ucm.web.domain.ListUserRecord;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
public class DataTableUtils {

    public static List<ListUserRecord> getListUserRecord(List<User> userList) {
        if (userList == null || userList.size() == 0) {
            return new ArrayList<>();
        }
        List<ListUserRecord> records = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(User user: userList) {
            ListUserRecord record = new ListUserRecord();
            record.setId(user.getId());
            record.setUsername(user.getUsername());
            record.setCreateTime(sdf.format(user.getCreateTime()));
            record.setUpdateTime(sdf.format(user.getUpdateTime()));
            records.add(record);
        }
        return records;
    }

    public static void dataTableResponse(HttpServletResponse response, DataTableResult result) throws IOException{
        String dataTableJson = JSON.toJSONString(result);
        byte[] data = dataTableJson.getBytes();
        response.setContentType("text/json");
        response.setContentLength(data.length);
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

    public static void dataTableErrorResponse(HttpServletResponse response, int draw, String message) throws IOException {
        DataTableResult dtr = new DataTableResult();
        dtr.setDraw(draw);
        dtr.setError(message);
        dataTableResponse(response, dtr);
    }

    public static List<ListProjectRecord> getListProjectRecord(List<Project> projectList) {

        if (projectList == null || projectList.size() == 0) {
            return new ArrayList<>();
        }
        List<ListProjectRecord> records = new ArrayList<>();
        for(Project project : projectList) {
            ListProjectRecord record = new ListProjectRecord();
            record.setId(project.getId());
            record.setCode(project.getCode());
            record.setName(project.getName());
            record.setType(ProjectType.getProjectType(Integer.parseInt(project.getType())).getDesc());
            records.add(record);
        }

        return records;
    }
}
