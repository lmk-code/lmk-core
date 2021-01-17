package com.lmk.core.web.monitor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.lmk.core.web.monitor.bean.LogAction;
import com.lmk.core.web.monitor.bean.LogLevel;
import com.lmk.core.web.monitor.bean.LogType;

/**
 * API 接口调用日志注解
 * @author LaoMake
 * @since 1.0
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseLog {

    /** 日志类型 */
    LogType type() default LogType.BACK;

    /** 日志级别 */
    LogLevel level() default LogLevel.debug;

    /** 日志行为 */
    LogAction action() default LogAction.Retrieve;

    /** 目标资源 */
    String resource() default "";

    /** 详情描述 */
    String content() default "";

    /** 是否保存到数据库 */
    boolean toDb() default false;
}
