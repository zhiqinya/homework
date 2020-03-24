package com.briup.demo.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 实现文章相关逻辑
 * @author DELL
 *
 */
@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void saveOrUpdateArtcle(Article article) throws CustomerException {
		if (article == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if (article.getId() == null) {
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articleMapper.insert(article);
		}else {
			article.setPublishdate(new Date());
			articleMapper.updateByPrimaryKey(article);
		}
		
	}

	@Override
	public void delectArtcleById(int id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Article> findArticleByCondtion(String keyStr, String condition) throws CustomerException {
		/**
		 * 三种情况
		 * 1.没有添加任何条件 则查询所有文章
		 * 2.没有指定栏目 指定了查询关键字 则根据关键字查询
		 * 3.指定栏目没有指定关键字
		 * 4.指定 栏目 指定关键字
		 */
		CategoryExample example2 = new CategoryExample();
		ArticleExample example = new ArticleExample();
		keyStr = keyStr==null? "" :keyStr.trim();
		condition = condition==null?"":condition.trim();
		if ("".equals(keyStr)&&"".equals(condition)) {
			//情况1
			return articleMapper.selectByExample(example);
		}else if(!"".equals(keyStr)&&"".equals(condition)) {
			//情况2
			example.createCriteria().andTitleLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
		}else if (!"".equals(condition)&&"".equals(keyStr)) {
			//情况3
			example2.createCriteria().andNameEqualTo(condition);
			//栏目名字不唯一所有导致返回值为list
			List<Category> category = categoryMapper.selectByExample(example2);
			if(category.size()>0)
			{	
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
				return articleMapper.selectByExample(example);				
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
			}
		}else{
			example2.createCriteria().andNameEqualTo(condition);
			List<Category> category = categoryMapper.selectByExample(example2);
			if(category.size()>0)
			{
			example.createCriteria().andCategoryIdEqualTo(category.get(0).getId()).andTitleLike("%"+keyStr+"%");
			//or 或  example.or().and
			return articleMapper.selectByExample(example);
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
			}
		}
		
	}

	@Override
	public Article findArticleById(int id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);
		Integer clickTime = article.getClicktimes() == null? 0 : article.getClicktimes();
		article.setClicktimes(clickTime+1);
		this.saveOrUpdateArtcle(article);
		return article;
	}

}
