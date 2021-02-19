package com.lmk.core.web.support.bean.db;

import com.lmk.core.commons.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询条件构建工具
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class Selector {

	/** SQL构建串 */
	private StringBuffer sql = new StringBuffer();
	
	/** 操作符 */
	@SuppressWarnings("serial")
	private static final Map<String, String> op = new HashMap<String, String>(){
		{
			put("EQ", "=");
			put("GT", ">");
			put("LT", "<");
			put("LIKE", "LIKE");
		}
		
	};
	
	/**
	 * 等于判断："="
	 * @param fieldName 属性名
	 * @param value 属性值
	 */
	public void eq(String fieldName, Object value){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" = '").append(value).append("'");
	}
	
	/**
	 * 不等于判断："!="
	 * @param fieldName 属性名
	 * @param value 属性值
	 */
	public void notEq(String fieldName, Object value){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" != '").append(value).append("'");
	}
	
	/**
	 * 大于判断，includeEquals用于界定 ">" 和 ">=" 
	 * @param fieldName 属性名
	 * @param value 属性值
	 * @param includeEqual 是否包含等于
	 */
	public void gt(String fieldName, Object value, Boolean includeEqual){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName);
		
		if(includeEqual)
			sql.append(" >= '").append(value).append("'");
		else
			sql.append(" > '").append(value).append("'");
	}
	
	/**
	 * 小于判断，includeEquals用于界定 "<" 和 "<=" 
	 * @param fieldName 属性名
	 * @param value 属性值
	 * @param includeEqual 是否包含等于
	 */
	public void lt(String fieldName, Object value, Boolean includeEqual){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName);
		
		if(includeEqual)
			sql.append(" <= '").append(value).append("'");
		else
			sql.append(" < '").append(value).append("'");
	}
	
	/**
	 * 模糊查询："like"
	 * @param fieldName 属性名
	 * @param value 属性值
	 */
	public void like(String fieldName, Object value){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" like '%").append(value).append("%'");
	}
	
	/**
	 * 区间查询："between"
	 * @param fieldName 属性名
	 * @param from 起始值
	 * @param to 截止值
	 */
	public void between(String fieldName, String from, String to){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" BETWEEN '").append(from).append("' To '").append(to).append("'");
	}
	
	/**
	 * 集合查询："in"
	 * @param fieldName 属性名
	 * @param values 属性值集合
	 */
	public void in(String fieldName, String... values){
		if(sql.length() > 0)
			sql.append(" AND ");
		
		sql.append(fieldName).append(" IN (");
		int size = values.length;
		for(int i = 0; i < size; i++){
			sql.append("'").append(values[i]).append("'");
			if(i < (size - 1))
				sql.append(", ");
		}
		sql.append(")");
	}
	
	/**
	 * 控制判断："IS NULL"
	 * @param fieldName 属性名
	 */
	public void isNull(String fieldName){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" IS NULL");
	}
	
	/**
	 * 非空判断："IS NOT NULL"
	 * @param fieldName 属性名
	 */
	public void notNull(String fieldName){
		if(sql.length() > 0)
			sql.append(" AND ");
		sql.append(fieldName).append(" IS NOT NULL");
	}
	
	/**
	 * 判断是否有查询条件
	 * @return
	 */
	public boolean hasContent(){
		return sql.length() > 0;
	}
	
	/**
	 * 解析查询参数，指定表名
	 * @param searchString
	 * @param tableName
	 */
	public void parseSearch(String searchString, String tableName){
		try {
			List<Search> searchList = JsonUtils.parseList(searchString, Search.class);
			if(searchList.size() > 0){
				String operator = null;
				
				for(Search search : searchList){
					if(StringUtils.isNotBlank(search.getName())){
						operator = search.getOperator();
						
						if(sql.length() > 0)
							sql.append(" AND ");
						if(tableName != null)
							sql.append(tableName).append(".");
						sql.append(search.getName());
						sql.append(" ").append(op.get(operator));
						if("LIKE".equals(operator)){
							sql.append(" '%").append(search.getValue()).append("%'");
						}else{
							sql.append(" '").append(search.getValue()).append("'");
						}
						 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return sql.toString();
	}
}
