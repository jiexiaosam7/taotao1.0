package com.taotao.manage.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemService;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public Long saveItem(Item item, String desc) {
		// 1、保存商品基本信息
		saveSelective(item);

		// 2、保存商品描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(itemDesc.getCreated());
		itemDescMapper.insertSelective(itemDesc);

		return item.getId();
	}

	@Override
	public void updateItem(Item item, String desc) {
		// 1、更新商品基本信息
		updateSelective(item);

		// 2、保存商品描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

}
