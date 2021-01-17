package com.lmk.core.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * DAO基础接口
 * @param <T>
 * @author LaoMake
 * @since 1.0
 */
public interface BaseDao<T> {
    /**
     * 新增一个对象
     * @param t
     */
    void insert(T t);

    /**
     * 按ID删除对象
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新一个对象
     * @param t
     */
    void update(T t);

    /**
     * 更新状态一般用于伪删除
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 按ID查询唯一对象
     * @param id
     * @return
     */
    T selectOne(Integer id);

    /**
     * 分页查询
     * @param search    查询条件（拼接成字符串）
     * @param sort      排序条件（拼接成字符串）
     * @param offset    起始位置
     * @param size  单页容量
     * @return
     */
    List<T> pageList(@Param("search") String search, @Param("sort") String sort, @Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 列表查询
     * @param search    查询条件（拼接成字符串）
     * @param sort      排序条件（拼接成字符串）
     * @return
     */
    List<T> loadList(@Param("search") String search, @Param("sort") String sort);

    /**
     * 按查询条件统计
     * @author LaoMake
     * @since 1.0
     * @param search
     * @return
     */
    Integer count(String search);
}
