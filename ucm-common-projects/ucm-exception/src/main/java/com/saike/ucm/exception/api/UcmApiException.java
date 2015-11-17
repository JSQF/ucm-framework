package com.saike.ucm.exception.api;

import com.meidusa.venus.annotations.RemoteException;
import com.saike.ucm.exception.UcmException;

/**
 * Created by huawei on 11/17/15.
 */
@RemoteException(errorCode = 400101000)
public class UcmApiException extends UcmException {
}
