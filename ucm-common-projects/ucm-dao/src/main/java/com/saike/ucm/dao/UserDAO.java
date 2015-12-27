package com.saike.ucm.dao;

import com.saike.ucm.domain.User;
import com.saike.ucm.domain.dao.Pagination;

import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
public interface UserDAO {

    void addUser(User user);

    User getUserByName(String username);

    User getUserById(Integer id);

    List<User> paginate(Pagination<User> condition);

    int paginateCount(Pagination<User> condition);


}
