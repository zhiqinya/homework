package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.briup.demo.bean.ex.User;
import com.briup.demo.dao.UserDao;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(description = "用户接口")
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/addUser")
	@ApiOperation(value = "新增用户")
	public Message<String> addUser(User user){
		userDao.save(user);
		return MessageUtil.success();
	}
	@GetMapping("/deleteUser")
	@ApiOperation(value = "删除用户")
	public Message<String> deleteUser(Integer username ){
		userDao.deleteById(username);
		return MessageUtil.success();
	}
	@PostMapping("/recordUser")
	@ApiOperation(value = "登录用户")
	public Message<String> recordUser(Integer username ,String password){
		System.out.println(1);
		List<User> list = userDao.findAll();
		System.out.println(list);
		for (User user : list) {
			int a = user.getUsername();
			if (a == username && user.getPassword().equals(password)) {				
				return MessageUtil.success();
			}
		}
		return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "密码或账号错误");
	}
}
