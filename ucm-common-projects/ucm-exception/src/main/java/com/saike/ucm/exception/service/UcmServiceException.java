package com.saike.ucm.exception.service;

import com.saike.ucm.exception.UcmException;

/**
 * Created by huawei on 12/26/15.
 */
public class UcmServiceException extends UcmException {
    public UcmServiceException() {
        super();
    }

    public UcmServiceException(String message) {
        super(message);
    }

    public UcmServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UcmServiceException(Throwable cause) {
        super(cause);
    }
}
