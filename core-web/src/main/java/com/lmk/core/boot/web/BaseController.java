package com.lmk.core.boot.web;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.lmk.core.boot.entity.IdEntity;
import com.lmk.core.boot.support.annotation.BootBaseService;
import com.lmk.core.boot.service.BaseService;
import com.lmk.core.commons.utils.BeanUtils;
import com.lmk.core.web.support.bean.db.PageParams;
import com.lmk.core.web.support.bean.db.Page;
import com.lmk.core.web.support.bean.db.Selector;
import com.lmk.core.web.support.api.RequestResult;
import com.lmk.core.web.support.api.StatusCode;
import com.lmk.core.web.support.api.GenericStatusCode;
import com.lmk.core.web.monitor.annotation.BaseLog;
import com.lmk.core.web.monitor.bean.LogLevel;

/**
 * 实现单表CRUD功能的抽象Controller <br/>
 * @param <T>
 * @author LaoMake
 * @since 1.0
 */
public abstract class BaseController<T extends IdEntity> {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    /** 实体类的类型 */
    protected Class<T> entityType;

    /** 资源名称 */
    protected String entityName;

    /** 实体服务，将会在运行过程中动态从子类对象获取 */
    protected BaseService<T> baseService;

    public BaseController() {

        // 获取子类的类型
        Class<?> clazz = this.getClass();

        // 父类的构造方法将会被子类调用，这里的this指向的是子类的对象
        // 获取实体类的类型
        this.entityType = BeanUtils.getSuperClassFirstGenerics(clazz);

        // 获取实体类名称（小写）
        String name = this.entityType.getSimpleName();
        this.entityName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    public BaseService<T> getBaseService() {
        if(this.baseService == null){
            Object service = BeanUtils.findFieldValueByAnnotation(this, BootBaseService.class);
            if(service != null){
                this.baseService = (BaseService<T>) service;
            } else {
                logger.error("注入 BaseService 失败：{}", this);
            }
        }
        return this.baseService;
    }

    public void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    /**
     * 分页查询
     * @param pageParams
     * @param request
     * @return
     */
    @BaseLog
    @GetMapping("")
    public RequestResult pageList(
            PageParams pageParams,
            HttpServletRequest request) {

        Selector selector = new Selector();
        parseSearch(selector, pageParams.getSearch()); // 解析查询参数
        beforeList(selector, pageParams, request);
        Page<T> pageResult = getBaseService().pageList(selector, pageParams);
        List<T> entityList = pageResult.getList();

        RequestResult result = new RequestResult();
        result.appendData("meta", getBaseService().loadMetaInfo());

        Map<String, Object> query = new HashMap<>();
        query.put("search", pageParams.getSearch());
        query.put("sort", pageParams.getSort());
        result.appendData("query", query);
        result.appendData("page", pageResult.getInfo());
        result.appendData("list", entityList);

        afterList(entityList, pageParams, request, result);

        return result;
    }

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @BaseLog
    @GetMapping("/{id}")
    public RequestResult findById(@PathVariable("id") Integer id){
        T bean = getBaseService().get(id);
        RequestResult result = new RequestResult();
        result.appendData("bean", bean);
        return result;
    }

    /**
     * 创建新对象
     * @param entity
     * @return
     */
    @BaseLog(level = LogLevel.info)
    @PostMapping("")
    public RequestResult create(@RequestBody T entity, HttpServletRequest request){
        StatusCode status = beforeCreate(entity, request);
        // 校验失败，可以提前返回结果
        if(status != GenericStatusCode.Success){
            return new RequestResult(status);
        }

        getBaseService().create(entity);

        RequestResult result = new RequestResult();
        result.appendData("id", entity.getId());
        afterCreate(entity, result, request);

        return result;
    }

    /**
     * 更新对象
     * @param entity
     * @return
     */
    @BaseLog(level = LogLevel.info)
    @PutMapping("")
    public RequestResult update(@RequestBody T entity, HttpServletRequest request){
        StatusCode status = beforeUpdate(entity, request);
        // 校验失败，可以提前返回结果
        if(status != GenericStatusCode.Success){
            return new RequestResult(status);
        }

        getBaseService().update(entity);

        RequestResult result = new RequestResult();
        result.appendData("id", entity.getId());
        afterUpdate(entity, result, request);

        return result;
    }

    /**
     * 根据ID删除
     * @param ids
     *          可以是单个id值，也可以是id列表，多个id之间用下划线连接
     * @return
     */
    @BaseLog(level = LogLevel.warn)
    @DeleteMapping("/{ids}")
    public RequestResult delete(@PathVariable("ids") String ids, HttpServletRequest request){
        String[] idList = ids.split("_");
        if(idList.length == 0){
            return new RequestResult(GenericStatusCode.ParameterError);
        }

        // 参数校验
        StatusCode status = beforeDelete(idList, request);
        if(status != GenericStatusCode.Success){
            return new RequestResult(status);
        }

        // 在Service中可能要操作缓存，@CacheEvict只有在被Controller直接调用时才会生效
        // 所有这里我们单独判断一下是否为单个ID删除
        if(idList.length == 1){
            getBaseService().delete(Integer.valueOf(idList[0]));
        }else{
            getBaseService().delete(idList);
        }

        // 构建返回对象
        RequestResult result = new RequestResult();
        afterDelete(idList, result, request);

        return result;
    }



    /**
     * 回调函数，在执行列表查询之前调用，可以修改过滤条件及排序条件
     *
     * @param selector
     *            添加过滤条件，如：<br/>
     *            selector.eq("status", 1)  <br/>
     *            selector.notEq("status", -1) <br/>
     *            selector.like("username", "san") <br/>
     *            <br/>
     * @param pageParams
     *            分页参数
     * @param request
     * @return
     */
    protected void beforeList(Selector selector, PageParams pageParams, HttpServletRequest request) {
    }

    /**
     * 回调函数，在执行列表查询之后调用
     * @param entityList
     * @param pageParams
     * @param request
     * @param result
     */
    protected void afterList(List<T> entityList, PageParams pageParams, HttpServletRequest request, RequestResult result) {
    }

    /**
     * 回调函数：在保存新对象之前调用，可以继续设置entity属性
     * @param entity
     * @param request
     * @return 校验码，仅StatusCode.Success为校验通过
     */
    protected StatusCode beforeCreate(T entity, HttpServletRequest request) {
        return GenericStatusCode.Success;
    }

    /**
     * 回调函数：在保存新对象之后调用
     * @param entity
     * @param result
     * @param request
     */
    protected void afterCreate(T entity, RequestResult result, HttpServletRequest request) {
    }

    /**
     * 回调函数：在更新对象之前调用，可以继续设置entity属性
     * @param entity
     * @param request
     * @return 校验码，仅StatusCode.Success为校验通过
     */
    protected StatusCode beforeUpdate(T entity, HttpServletRequest request) {
        return GenericStatusCode.Success;
    }

    /**
     * 回调函数：在更新对象之后调用
     * @param entity
     * @param result
     * @param request
     */
    protected void afterUpdate(T entity, RequestResult result, HttpServletRequest request) {
    }

    /**
     * 回调函数：在删除对象之前调用，可以校验用户权限
     * @param idList
     * @param request
     * @return 校验码，仅StatusCode.Success为校验通过
     */
    protected StatusCode beforeDelete(String[] idList, HttpServletRequest request) {
        return GenericStatusCode.Success;
    }

    /**
     * 回调函数：在删除对象之后调用
     * @param idList
     * @param result
     * @param request
     */
    protected void afterDelete(String[] idList, RequestResult result, HttpServletRequest request) {
    }

    /**
     * 解析查询参数
     * @param selector
     * @param search
     */
    protected void parseSearch(Selector selector, String search){
        parseSearch(selector, search, null);
    }

    /**
     * 解析查询参数，指定表名
     * @param selector
     * @param search
     * @param tableName
     */
    protected void parseSearch(Selector selector, String search, String tableName) {
        if (StringUtils.isNotBlank(search)) {
            try {
                search = new String(Base64.decodeBase64(search), "UTF-8");
                selector.parseSearch(search, tableName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取实体类类型
     * @return
     */
    public Class<T> getEntityType() {
        return entityType;
    }

    /**
     * 获取实体类名
     * @return
     */
    public String getEntityName() {
        return entityName;
    }

}