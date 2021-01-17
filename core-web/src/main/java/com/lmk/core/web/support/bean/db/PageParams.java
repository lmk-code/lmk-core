package com.lmk.core.web.support.bean.db;

import java.io.Serializable;

/**
 * 分页查询参数
 * @author LaoMake
 * @since 1.0
 */
public class PageParams implements Serializable {
    /** 当前页码 */
    private Integer page = 1;

    /** 单页容量 */
    private Integer size = 10;

    /** 查询条件 */
    private String search;

    /** 排序条件, 如：id_ASC, age_DESC等 */
    private String sort = "id_DESC";

    public PageParams() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
