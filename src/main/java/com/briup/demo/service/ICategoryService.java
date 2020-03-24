package com.briup.demo.service;

import java.util.List;


import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 栏目相关的Service层
 * @author DELL
 *
 */

public interface ICategoryService {
	/**
	 * 查询所有栏目
	 * @return
	 * @throws CustomerException
	 */
	 List<Category> findAllCategorys() throws CustomerException;
	 
	 /**
	  * 添加或修改栏目信息
	  */
	 void saveOrUpdateCatgory(Category category) throws CustomerException;
	 /**
	  * 根据id删除
	  */
	 void deleteCatgoeyById(int id) throws CustomerException;
	 /**
	  * 根据栏目id进行查找
	  */
	 Category findCategoriesById(int id) throws CustomerException;
	 /**
	  * 查询栏目信息并且级联查询包含的文章信息
	  */
	 List<CategoryEx> findAllCategoryEx() throws CustomerException;
	 /**
		 * 查询栏目及其包含的文章
		 * @param id
		 * @return
		 */
	 CategoryEx findCategoryExById(int id);
}
