package com.briup.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import com.briup.demo.bean.ex.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
