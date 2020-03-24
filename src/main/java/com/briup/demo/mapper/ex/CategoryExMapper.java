package com.briup.demo.mapper.ex;
/**
 * 处理查询栏目及其包含的文章信息
 * @author DELL
 *
 */

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

public interface CategoryExMapper {
	/**
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryExs();
	
	/**
	 * 查询栏目及其包含的文章
	 * @param id
	 * @return
	 */
	CategoryEx findCategoryExById(int id);
}
