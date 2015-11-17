package com.saike.ucm.exception;

/**
 * Created by huawei on 11/17/15.
 */
public class UcmException extends Exception {
    public UcmException() {
    }

    public UcmException(String message) {
        super(message);
    }

    public UcmException(String message, Throwable cause) {
        super(message, cause);
    }

    public UcmException(Throwable cause) {
        super(cause);
    }
}
