package com.briup.demo.bean.ex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cms_user")
public class User {
	@Id
	@GeneratedValue
	private Integer username;
	private String password;
	public User() {
		super();
	}
	public User(Integer username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Integer getUsername() {
		return username;
	}
	public void setUsername(Integer username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
