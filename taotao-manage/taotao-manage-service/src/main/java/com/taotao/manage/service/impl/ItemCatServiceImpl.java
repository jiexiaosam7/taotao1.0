package com.taotao.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<ItemCat> queryItemCatListInPage(Integer pageNo, Integer rows) {
		//设置分页
		PageHelper.startPage(pageNo, rows);
		
		return itemCatMapper.select(null);
	}
}
