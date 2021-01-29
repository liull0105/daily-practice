package com.demo.pra.web.distributedlock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复请求注解
 *
 * @date: 2021/1/29 16:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {


    /**
     * 设置请求的锁定时间
     *
     * @date: 2021/1/29 16:24
     */
    int lockTime() default 10;

}
