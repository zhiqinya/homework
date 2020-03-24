package com.briup.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryExMapper categoryExMapper;
	/**
	 * 查找所有
	 */
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}
	/**
	 * 添加修改
	 */
	@Override
	public void saveOrUpdateCatgory(Category category) throws CustomerException {
		CategoryExample example = new CategoryExample();
		if(category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//id为空插入不为空修改
		if (category.getId() == null) {
			List<Category> list = categoryMapper.selectByExample(example);
			for (Category category2 : list) {
				if (category2.getName().equals(category.getName())||category2.getCode()==category.getCode()) {
					throw new CustomerException(StatusCodeUtil.EXISTS_CODE, "栏目存在");
				}
			}
			categoryMapper.insert(category);
		}else {
			categoryMapper.updateByPrimaryKey(category);
		}
	}
	/**
	 * 删除根据id
	 */
	@Override
	public void deleteCatgoeyById(int id) throws CustomerException {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
		
	}
	/**
	 * 根据id查找
	 */
	@Override
	public Category findCategoriesById(int id) throws CustomerException {
		
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}
	/**
	 * 所有栏目及其包含的文章信息
	 */
	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}
	/**
	 * 查询栏目及其包含的文章
	 */
	@Override
	public CategoryEx findCategoryExById(int id) {
	
		return 	categoryExMapper.findCategoryExById(id);
	}
	

}
