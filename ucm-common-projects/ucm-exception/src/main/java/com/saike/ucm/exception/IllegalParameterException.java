package com.saike.ucm.exception;

/**
 * Created by huawei on 12/28/15.
 */
public class IllegalParameterException extends UcmException {
    public IllegalParameterException() {
        super();
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }
}
