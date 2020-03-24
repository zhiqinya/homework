package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 * 文章相关内容的 service接口
 * @author DELL
 *
 */
public interface IArticleService {
	
	/**
	 * 新增修改文章
	 */
	void saveOrUpdateArtcle(Article article) throws CustomerException;
	
	/**
	 * 删除文章
	 */
	void delectArtcleById( int id) throws CustomerException;
	
	/**
	 * 
	 * @param keyStr 表示搜素框
	 * @param condition 表示栏目框
	 * @return
	 * @throws CustomerException
	 */
	List<Article> findArticleByCondtion(String keyStr,String condition) throws CustomerException;
	
	/**
	 * 根据id查询文章
	 */
	Article findArticleById(int id) throws CustomerException;
	
	
}
