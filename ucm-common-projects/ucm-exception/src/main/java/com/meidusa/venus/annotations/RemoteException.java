package com.meidusa.venus.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteException {

    /**
     * 错误编码
     *
     * @return
     */
    int errorCode();
}
