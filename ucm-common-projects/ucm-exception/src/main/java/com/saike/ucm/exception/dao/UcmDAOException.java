package com.saike.ucm.exception.dao;

import com.saike.ucm.exception.UcmException;

/**
 * Created by huawei on 12/26/15.
 */
public class UcmDAOException extends UcmException {
    public UcmDAOException() {
        super();
    }

    public UcmDAOException(String message) {
        super(message);
    }

    public UcmDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public UcmDAOException(Throwable cause) {
        super(cause);
    }
}
