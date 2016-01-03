package com.saike.ucm.web.utils;

import com.meidusa.fastjson.JSON;
import com.saike.ucm.domain.*;
import com.saike.ucm.web.domain.*;
import redis.clients.jedis.Jedis;

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

    public static List<ListEnvironmentRecord> getListEnvironmentRecord(List<Environment> results) {
        if (results == null || results.size() == 0) {
            return new ArrayList<>();
        }
        List<ListEnvironmentRecord> records = new ArrayList<>();
        for(Environment environment : results) {
            ListEnvironmentRecord record = new ListEnvironmentRecord();
            record.setId(environment.getId());
            record.setName(environment.getName());
            record.setCode(environment.getCode());
            record.setOrder(environment.getOrder());
            record.setTotal(results.size());
            record.setStatusDesc(environment.isActive() ?"启用" : "停用");
            records.add(record);
        }
        return records;
    }

    public static List<ListEnvironmentIpRecord> getListEnvironmentIpRecord(List<EnvironmentIp> allEnvironmentIp) {
        if (allEnvironmentIp == null || allEnvironmentIp.size() == 0) {
            return new ArrayList<>();
        }
        List<ListEnvironmentIpRecord> records = new ArrayList<>();
        for(EnvironmentIp environmentIp: allEnvironmentIp) {
            ListEnvironmentIpRecord record = new ListEnvironmentIpRecord();
            record.setId(environmentIp.getId());
            record.setEnvironmentId(environmentIp.getEnvironmentId());
            record.setIp(environmentIp.getIp());
            records.add(record);
        }
        return records;
    }

}
