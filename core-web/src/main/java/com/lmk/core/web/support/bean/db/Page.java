package com.lmk.core.web.support.bean.db;

import java.util.List;

/**
 * 分页结果
 * @param <T>
 *
 * @author LaoMake
 * @since 1.0
 */
public class Page<T> {

	/** 分页信息 */
	PageInfo info;

    /** 对象列表 */
    private List<T> list;

	public Page() {
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
