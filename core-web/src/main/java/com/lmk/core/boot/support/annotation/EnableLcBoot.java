package com.lmk.core.boot.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import com.lmk.core.boot.support.processor.LcBootBeanPostProcessor;

/**
 * 启用 Laomake-SpringBoot 功能模块
 * @author LaoMake
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        LcBootBeanPostProcessor.class
})
public @interface EnableLcBoot {
}
