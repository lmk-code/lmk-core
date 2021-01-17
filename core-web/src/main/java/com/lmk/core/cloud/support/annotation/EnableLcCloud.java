package com.lmk.core.cloud.support.annotation;

import com.lmk.core.cloud.support.processor.LcCloudBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Laomake-SpringCloud 功能模块
 * @author LaoMake
 * @since 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        LcCloudBeanPostProcessor.class
})
public @interface EnableLcCloud {
}
