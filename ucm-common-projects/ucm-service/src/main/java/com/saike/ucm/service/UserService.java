package com.saike.ucm.service;

import com.saike.ucm.domain.PaginationResult;
import com.saike.ucm.domain.User;
import com.saike.ucm.exception.service.UcmServiceException;

public interface UserService {

    void addUser(String username) throws UcmServiceException;

    PaginationResult<User> paginate(String username, int start, int length) throws UcmServiceException;



}
