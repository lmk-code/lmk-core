package com.lmk.core.boot.entity;

import java.io.Serializable;

/**
 * 含有ID主键的抽象基类
 * @author LaoMake
 * @since 1.0
 *
 */
@SuppressWarnings("serial")
public class IdEntity implements Serializable {
	
	/** 主键ID */
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
