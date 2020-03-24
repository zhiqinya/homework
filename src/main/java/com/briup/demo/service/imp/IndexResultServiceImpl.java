package com.briup.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.briup.demo.bean.Link;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;

/**
 * 查询首页所有数据的实现类
 * @author DELL
 *
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService {
	


	
	//关联超链接service层接口
	@Autowired
	private ILinkService linkService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public IndexResult findIndexAllResult() throws CustomerException {
		
		IndexResult indexResult = new IndexResult();
		List<Link> links = linkService.findAlllinks();
		indexResult.setLinks(links);
		List<CategoryEx> categoryExs = categoryService.findAllCategoryEx();
		indexResult.setCategoryExs(categoryExs);
		return indexResult;
	}
	


}
