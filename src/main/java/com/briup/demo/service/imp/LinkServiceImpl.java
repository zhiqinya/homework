package com.briup.demo.service.imp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 操作链接的service功能
 * @author DELL
 *
 */
@Service
public class LinkServiceImpl implements ILinkService {
	
	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public void savaOrUpdateLink(Link link) throws CustomerException {
		if(link == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//id为空插入不为空修改
		if (link.getId() == null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
		
	}
	@Override
	public void deleteLinkById(int id) throws CustomerException {
		linkMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Link> findAlllinks() throws CustomerException {
		LinkExample example = new LinkExample();
		List<Link> list = linkMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<Link> findLinksByName(String name) throws CustomerException {
		LinkExample example = new LinkExample();
		name = name== null ? "" : name.trim();
		if ("".equals(name)) {
			//搜素条件没写 则返回所有数据
			List<Link> list = linkMapper.selectByExample(example);
			return list;
		}else {
			//不为空 则返回满足条件的数据
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");
			List<Link> list = linkMapper.selectByExample(example);
			return list;
		}
	}
	
}
