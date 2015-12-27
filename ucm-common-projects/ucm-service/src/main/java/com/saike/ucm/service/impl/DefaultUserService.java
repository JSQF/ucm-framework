package com.saike.ucm.service.impl;

import com.saike.ucm.dao.UserDAO;
import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.User;
import com.saike.ucm.domain.dao.Pagination;
import com.saike.ucm.exception.dao.UcmDAOException;
import com.saike.ucm.exception.service.UcmServiceException;
import com.saike.ucm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
public class DefaultUserService implements UserService {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

    private UserDAO userDAO;

    @Override
    public void addUser(String username) throws UcmServiceException {

        try{
            if(logger.isDebugEnabled()) {
                logger.debug("add user: {}", username);
            }

            User user = this.userDAO.getUserByName(username);
            if (user == null) {
                if(logger.isDebugEnabled()) {
                    logger.debug("{} get db is null");
                }
                user = new User();
                user.setUsername(username);
                Date date = new Date();
                user.setCreateTime(date);
                user.setUpdateTime(date);
                this.userDAO.addUser(user);
            }
        }catch (Exception e) {
            throw new UcmServiceException("保存用户异常:", e);
        }

    }

    @Override
    public PaginationResult<User> paginate(String username, int start, int length) throws UcmServiceException {
        Pagination<User> condition = new Pagination<>();
        User queryUser = new User();
        queryUser.setUsername(username);
        condition.setCondition(queryUser);
        condition.setStart(start);
        condition.setLength(length);
        try{
            List<User> userList = userDAO.paginate(condition);
            int count = userDAO.paginateCount(condition);
            PaginationResult<User> results = new PaginationResult<>();
            results.setResults(userList);
            results.setCount(count);
            return results;
        }catch(Exception e) {
            throw new UcmServiceException("分页查询用户异常:", e);
        }

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
