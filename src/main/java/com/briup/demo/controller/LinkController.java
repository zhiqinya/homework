package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与链接相关的 和前端交互的web层
 * @author DELL
 *
 */
@RestController
@Api(description =  "链接相关接口")
public class LinkController {
	@Autowired
	private ILinkService linkService;
	
	@PostMapping("/addLink")
	@ApiOperation(value = "新增链接")
	public Message<String> addLink(Link link){
		try {
			linkService.savaOrUpdateLink(link);
			return MessageUtil.success();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE,"系统错误"+e.getMessage());
		}
	}
	
	@PostMapping("/updateLink")
	@ApiOperation(value = "修改链接")
	public Message<String> updateLink(Link link){
		try {
			linkService.savaOrUpdateLink(link);
			return MessageUtil.success();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE,"系统错误"+e.getMessage());
		}
	}
	
	@GetMapping("/deleteLink")
	@ApiOperation(value = "根据id删除链接")
	public Message<String> deleteLink(int id){
			linkService.deleteLinkById(id);
			return MessageUtil.success();
	}
	
	
	@GetMapping("/selectLinks")
	@ApiOperation(value = "查找所有链接")
	public Message<List<Link>> selectLink(){
		List<Link> list = linkService.findAlllinks();
		for (Link link : list) {
			System.out.println(link);
		}
		return MessageUtil.success(list);
	}
	
	@GetMapping("/selectLinkByName")
	@ApiOperation("根据链接名查询")
	public Message<List<Link>> selectLinkByName(String name){
	
		return MessageUtil.success(linkService.findLinksByName(name));
	}
	
}
