

package com.githhub.codeman.core.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface RateLimit {

    
    int value() default 1;

    
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    
    long interval() default 60;

    
    long count() default 1000;

    
    boolean enable() default true;

}
