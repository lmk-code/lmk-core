package com.lmk.core.boot.support.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于标识 BaseService 的注解。
 * 注意：一个Controller中只能包含一个此注解，包含多个则只有第一个才会生效
 * @author LaoMake
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BootBaseService {
}
