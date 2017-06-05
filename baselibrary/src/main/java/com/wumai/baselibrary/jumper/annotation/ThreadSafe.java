package com.wumai.baselibrary.jumper.annotation;

public @interface ThreadSafe {

    boolean value() default true;
}
