package com.saike.ucm.client.exception;

/**
 * Created by huawei on 11/17/15.
 */
public class ClientException extends Exception {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }
}
