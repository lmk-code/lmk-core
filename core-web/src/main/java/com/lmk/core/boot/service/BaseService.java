package com.lmk.core.boot.service;

import java.util.List;

import com.lmk.core.boot.dao.BaseDao;
import com.lmk.core.web.support.bean.db.Page;
import com.lmk.core.web.support.bean.db.Selector;
import com.lmk.core.web.support.bean.db.PageParams;
import com.lmk.core.boot.support.bean.Meta;
import com.lmk.core.boot.entity.IdEntity;

/**
 * 基础的CRUD服务接口
 * @param <T>
 * @author LaoMake
 * @since 1.0
 */
public interface BaseService<T extends IdEntity> {

    /**
     * 设置BaseDao对象
     */
    void setBaseDao(BaseDao<T> baseDao);

    /**
     * 加载模块元素数据
     * @return
     */
    Meta loadMetaInfo();

    /**
     * 添加实体对象
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * 更新实体对象
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 更新状态，一般用于伪删除
     * @param id
     * @param status
     */
    void updateStatus(Integer id, Integer status);

    /**
     * 按ID删除对象
     * @author LaoMake
     * @since 1.0
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据ID列表删除
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 按ID获取对象
     * @author LaoMake
     * @since 1.0
     * @param id
     * @return
     */
    T get(Integer id);

    /**
     * 分页查询
     * @param selector
     * 			查询条件，使用方法请参考Selector类
     * @param pageParams
     * 			分页参数
     * @return
     */
    Page<T> pageList(Selector selector, PageParams pageParams);

    /**
     * 列表查询
     * @param selector
     * @param sort
     * @return
     */
    List<T> loadList(Selector selector, String sort);
}
