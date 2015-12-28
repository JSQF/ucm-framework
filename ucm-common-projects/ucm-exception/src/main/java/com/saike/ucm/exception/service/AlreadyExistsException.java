package com.saike.ucm.exception.service;

/**
 * Created by huawei on 12/28/15.
 */
public class AlreadyExistsException extends UcmServiceException {

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
