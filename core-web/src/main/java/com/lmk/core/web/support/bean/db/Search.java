package com.lmk.core.web.support.bean.db;

/**
 * 查询过滤器
 * @author LaoMake
 * @since 1.0
 */
public class Search {
	
	/** 查询字段 */
	private String name;
	
	/** 判断值 */
	private String value;
	
	/** 操作符 */
	private String operator;
	
	public Search() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String fieldName) {
		this.name = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String fieldValue) {
		this.value = fieldValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Search{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				", operator='" + operator + '\'' +
				'}';
	}
}
