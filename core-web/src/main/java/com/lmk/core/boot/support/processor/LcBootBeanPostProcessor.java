package com.lmk.core.boot.support.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import com.lmk.core.boot.dao.BaseDao;
import com.lmk.core.boot.service.BaseService;
import com.lmk.core.boot.support.annotation.BootBaseDao;
import com.lmk.core.boot.support.annotation.BootBaseService;
import com.lmk.core.boot.web.BaseController;
import com.lmk.core.commons.utils.AopUtils;
import com.lmk.core.commons.utils.BeanUtils;

/**
 * Laomake-SpringBoot 模块后置处理器
 * @author LaoMake
 * @since 1.0
 */
public class LcBootBeanPostProcessor implements BeanPostProcessor {

    Logger logger = LoggerFactory.getLogger(LcBootBeanPostProcessor.class);

    /**
     * 这里需要对BaseController、BaseService的子类进行参数解析、注入
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        if(bean instanceof BaseController){
            BaseController baseController = (BaseController) bean;

            // 处理 BaseController 的子类对象
            Field field = BeanUtils.findFieldByAnnotation(clazz, BootBaseService.class);
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
        }else if(bean instanceof BaseService){
            // 处理 BaseService 的子类对象
            BaseService baseService = (BaseService) bean;

            // 开启事务后，service对象会被代理，这里需要单独处理
            if(bean instanceof Proxy){
                try {
                    baseService = (BaseService) AopUtils.getJdkDynamicProxyTargetObject(bean);
                    clazz = baseService.getClass();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 注入 BaseDao
            Field baseDaoField = BeanUtils.findFieldByAnnotation(clazz, BootBaseDao.class);
            if(baseDaoField != null){
                try {
                    baseDaoField.setAccessible(true);
                    baseService.setBaseDao((BaseDao) baseDaoField.get(baseService));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                logger.error("注入 BaseDao 失败：{}", beanName);
            }
        }
        return bean;
    }
}
