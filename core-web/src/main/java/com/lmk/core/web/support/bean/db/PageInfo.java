package com.lmk.core.web.support.bean.db;

/**
 * 分页信息
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class PageInfo {
    /** 当前页码 */
    private Integer pageNo;

    /** 当前页容量 */
    private Integer pageSize;

    /** 总页数 */
    private Integer pageTotal;

    /** 起始序号 */
    private Integer first;

    /** 截止序号 */
    private Integer last;

    /** 当前页记录数 */
    private Integer size;

    /** 总体记录数 */
    private Integer rows;

    public PageInfo() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
