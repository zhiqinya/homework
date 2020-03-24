package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.utils.CustomerException;

/**
 * 关于链接的相关操作
 * @author DELL
 *
 */
public interface ILinkService {
	/**
	 * 保存或修改链接信息
	 * @param link
	 */
	void savaOrUpdateLink(Link link) throws CustomerException;
	/**
	 * 删除
	 * @param link
	 * @throws CustomerException
	 */
	void deleteLinkById(int id) throws CustomerException;
	/**
	 *  查询所有链接
	 * @param link
	 * @return link
	 * @throws CustomerException
	 */
	List<Link> findAlllinks() throws CustomerException;
	/**
	 * 根据 链接名 查询链接
	 */
	List<Link> findLinksByName(String name) throws CustomerException;
}
