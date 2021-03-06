package com.briup.demo.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.demo.bean.Article;

import io.swagger.annotations.ApiParam;

/**
 * 栏目的扩展类
 * 		除了栏目信息还会及联保存栏目对应的文章的信息
 * @author DELL
 *
 */
public class CategoryEx implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 private Integer id;
	    
	    @ApiParam(required = true,value = "栏目代码")
	    private Long code;
	    @ApiParam(required = true,value = "栏目名称")
	    private String name;
	 //添加级联的所有文章
	    private List<Article> articles;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Long getCode() {
			return code;
		}
		public void setCode(Long code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Article> getArticles() {
			return articles;
		}
		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
		public CategoryEx(Integer id, Long code, String name, List<Article> articles) {
			super();
			this.id = id;
			this.code = code;
			this.name = name;
			this.articles = articles;
		}
		public CategoryEx() {
			super();
		}
	    

}
