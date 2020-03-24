package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 文章相关信息的Controller 
 * @author DELL
 *
 */
@RestController
@Api(description = "文章相关接口")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ArticleMapper articleMapper;
	
	
	@PostMapping("/addArticle")
	@ApiOperation("/添加文章信息")
	public Message<String> saveArticle(Article article){
		try {
			articleService.saveOrUpdateArtcle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	@PostMapping("/updateArticle")
	@ApiOperation("/修改文章信息")
	public Message<String> updateArticle(Article article){
		try {
			articleService.saveOrUpdateArtcle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	@GetMapping("/deleteArticle")
	@ApiOperation("/根据id删除文章信息")
	public Message<String> deteleArticle(Integer id){
			articleService.delectArtcleById(id);
			return MessageUtil.success();
		
	}
	@GetMapping("/findArticleByCondition")
	@ApiOperation("/根据条件查询文章信息")
	public Message<List<Article>> getArticleByCondition(String keyStr,String condition){
			try {
				List<Article> list = articleService.findArticleByCondtion(keyStr, condition);
				return MessageUtil.success(list);
			} catch (CustomerException e) {
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "栏目不存在"+e.getMessage());
			}

	}
	@GetMapping("/findArticleById")
	@ApiOperation("/根据id查询文章信息")
	public Message<Article> getArticleById(int id){
			 Article article = articleService.findArticleById(id);
			return MessageUtil.success(article);

	}
	@GetMapping("/lookArticle")
	@ApiOperation("/游览")
	public Message<Article> lookArticle(String name){
			ArticleExample example = new ArticleExample();
			example.createCriteria().andTitleEqualTo(name);
			List<Article> article = articleMapper.selectByExample(example);
			articleMapper.deleteByPrimaryKey(article.get(0).getId());
			Article article2 = new Article();
			article2.setAuthor(article.get(0).getAuthor());
			article2.setCategoryId(article.get(0).getCategoryId());
			article2.setClicktimes(article.get(0).getClicktimes()+1);
			article2.setContent(article.get(0).getContent());
			article2.setId(article.get(0).getId());
			article2.setPublishdate(article.get(0).getPublishdate());
			article2.setTitle(article.get(0).getTitle());
			articleMapper.insert(article2);
			return MessageUtil.success(article2);
	}
}
