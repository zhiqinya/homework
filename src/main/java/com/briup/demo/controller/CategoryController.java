package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.imp.ArticleServiceImpl;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private IArticleService ArticleServiceImpl;
	
	@GetMapping("/addCategory")
	@ApiOperation(value = "新增栏目")
	public Message<String> addCategory(Category category){
			try {
				categoryService.saveOrUpdateCatgory(category);
				return MessageUtil.success();
			} catch (CustomerException e) {
				return MessageUtil.error(StatusCodeUtil.EXISTS_CODE,"系统错误"+e.getMessage());
			}
	}
		
	
	@GetMapping("/updateCategory")
	@ApiOperation(value = "更新栏目")
	public Message<String> updateCategory(Category category){
		try {
			categoryService.saveOrUpdateCatgory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE,"系统错误"+e.getMessage());
		}
	}
	
	@GetMapping("/deleteCategory")
	@ApiOperation(value = "删除栏目")
	public Message<String> deleteCategory(int id){
	
		
		categoryService.deleteCatgoeyById(id);
		return MessageUtil.success();
		
	}
	@GetMapping("/getCategory")
	@ApiOperation(value = "/根据id查找栏目")
	public Message<Category> getCategory(int id){
		Category category = categoryService.findCategoriesById(id);
		return MessageUtil.success(category);
		
	}
	@GetMapping("/getAllCategory")
	@ApiOperation(value = "/查找所有栏目")
	public Message<List<Category>> getAllCategory(){
		List<Category> list = categoryService.findAllCategorys();
		return MessageUtil.success(list);
		
	}
	@GetMapping("/getArticle")
	@ApiOperation(value = "/根据名字查找栏目")
	public Message<List<Article>> getArticle(String name){
		List<Article> list = ArticleServiceImpl.findArticleByCondtion(null, name);
		return MessageUtil.success(list);
	}
	
	@GetMapping("/findCategoryExByid")
	@ApiOperation(value = "/跟据id查找栏目及其文章信息")
	public Message<CategoryEx> findCategoryExByid (int id){
		return MessageUtil.success(categoryService.findCategoryExById(id));
	}
}
