package com.lmk.core.cloud.web;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.lmk.core.cloud.service.BaseService;
import com.lmk.core.cloud.support.annotation.CloudBaseService;
import com.lmk.core.commons.utils.BeanUtils;
import com.lmk.core.web.support.api.RequestResult;
import com.lmk.core.web.support.bean.db.PageParams;

/**
 * 基础的远程服务调用控制器
 */
public abstract class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    /** 基于OpenFeign的服务调用 */
    protected BaseService baseService;

    public BaseService getBaseService() {
        if(this.baseService == null){
            Object service = BeanUtils.findFieldValueByAnnotation(this, CloudBaseService.class);
            if(service != null){
                this.baseService = (BaseService) service;
            } else {
                logger.error("注入 BaseService 失败：{}", this);
            }
        }
        return this.baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * 分页查询
     * @param pageParams
     * @return
     */
    @GetMapping("")
    public RequestResult pageList(
            PageParams pageParams) {

        return getBaseService().list(pageParams);
    }

    /**
     * 按ID查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RequestResult findById(@PathVariable("id") Integer id){
        return getBaseService().findById(id);
    }

    /**
     * 创建
     * @param entity
     * @return
     */
    @PostMapping("")
    public RequestResult create(@RequestBody Map<String, Object> entity){
        return getBaseService().create(entity);
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @PutMapping("")
    public RequestResult update(@RequestBody Map<String, Object> entity){
        return getBaseService().update(entity);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public RequestResult delete(@PathVariable("ids") String ids){
        return getBaseService().delete(ids);
    }

}
