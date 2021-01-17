package com.lmk.core.cloud.support.processor;

import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import com.lmk.core.cloud.service.BaseService;
import com.lmk.core.cloud.support.annotation.CloudBaseService;
import com.lmk.core.cloud.web.BaseController;
import com.lmk.core.commons.utils.BeanUtils;

/**
 * Laomake-SpringCloud 模块后置处理器
 * @author LaoMake
 * @since 1.0
 */
public class LcCloudBeanPostProcessor implements BeanPostProcessor {

    Logger logger = LoggerFactory.getLogger(LcCloudBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();
        if(bean instanceof BaseController){
            BaseController baseController = (BaseController) bean;

            // 处理 BaseController 的子类对象
            Field field = BeanUtils.findFieldByAnnotation(clazz, CloudBaseService.class);
            if(field != null){
                try {
                    field.setAccessible(true);
                    baseController.setBaseService((BaseService) field.get(bean));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                logger.error("注入 BaseService 失败：{}", beanName);
            }
        }

        return bean;
    }
}
