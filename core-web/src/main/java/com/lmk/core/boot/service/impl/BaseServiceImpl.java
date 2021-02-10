package com.lmk.core.boot.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import com.lmk.core.boot.support.annotation.BootBaseDao;
import com.lmk.core.commons.utils.BeanUtils;
import com.lmk.core.boot.support.bean.Meta;
import com.lmk.core.boot.entity.IdEntity;
import com.lmk.core.web.support.bean.Status;
import com.lmk.core.web.support.bean.db.Page;
import com.lmk.core.web.support.bean.db.PageInfo;
import com.lmk.core.web.support.bean.db.Selector;
import com.lmk.core.web.support.bean.db.PageParams;
import com.lmk.core.boot.dao.BaseDao;
import com.lmk.core.boot.service.BaseService;

/**
 * 实现BaseService接口的抽象基类
 * @param <T>
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public abstract class BaseServiceImpl<T extends IdEntity> implements BaseService<T> {

    Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    /** 实体DAO，将会在运行过程中动态从子类对象获取 */
    protected BaseDao<T> baseDao;

    /**
     * 获取实体DAO，如果为空则从子类对象中动态获取
     * @return
     */
    private BaseDao<T> getBaseDao(){
        if(this.baseDao == null){
            Object dao = BeanUtils.findFieldValueByAnnotation(this, BootBaseDao.class);
            if(dao != null){
                this.baseDao = (BaseDao<T>) dao;
            } else {
                logger.error("注入 BaseDao 失败：{}", this);
            }
        }
        return baseDao;
    }

    @Override
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Meta loadMetaInfo() {
        return new Meta();
    }

    @Override
    public T create(T entity) {
        getBaseDao().insert(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        getBaseDao().update(entity);
        return entity;
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        getBaseDao().updateStatus(id, status);
    }

    @Override
    public void delete(Integer id) {
        // getBaseDao().deleteById(id);
        // 默认调整为伪删除
        getBaseDao().updateStatus(id, Status.REMOVE.getCode());
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            delete(Integer.valueOf(id));
        }
    }

    @Override
    public T get(Integer id) {
        return getBaseDao().selectOne(id);
    }

    @Override
    public Page<T> pageList(Selector selector, PageParams pageParams) {
        //构建查询参数
        String search = selector.hasContent() ? selector.toString() : null;
        String sort = pageParams.getSort();
        Integer pageNo = pageParams.getPage();
        Integer pageSize = pageParams.getSize();
        Integer offset = (pageNo - 1) * pageSize;

        if(StringUtils.isNoneBlank(sort)){
            int index = sort.lastIndexOf("_");
            sort = sort.substring(0, index) + " " + sort.substring(index + 1);
        }

        List<T> content = getBaseDao().pageList(search, sort, offset, pageSize);
        Integer totalRows = getBaseDao().count(search);
        Integer firstRow = (pageNo - 1) * pageSize;

        //封装查询结果
        Page<T> pageResult = new Page<T>();
        pageResult.setList(content);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(content.size());

        if(totalRows > 0)
            pageInfo.setFirst(firstRow + 1);
        else
            pageInfo.setFirst(0);

        pageInfo.setLast(firstRow + content.size());
        pageInfo.setRows(totalRows);

        if((totalRows % pageSize) == 0)
            pageInfo.setPageTotal((totalRows/pageSize));
        else
            pageInfo.setPageTotal((totalRows/pageSize + 1));

        pageResult.setInfo(pageInfo);

        return pageResult;
    }

    @Override
    public List<T> loadList(Selector selector, String sort) {
        //构建查询参数
        String search = selector.hasContent() ? selector.toString() : null;
        if(StringUtils.isBlank(sort)){
            sort = "id DESC";
        }else{
            sort = sort.replace("_", " ");
        }
        return getBaseDao().loadList(search, sort);
    }
}
