package com.meidusa.venus.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String name();

    String defaultValue() default "";

    boolean optional() default false;
}
