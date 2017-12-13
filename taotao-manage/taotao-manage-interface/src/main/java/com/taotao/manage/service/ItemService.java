package com.taotao.manage.service;

import com.taotao.manage.pojo.Item;

public interface ItemService extends BaseService<Item> {

	/**
	 * 保持商品基本和描述信息
	 * @param item 基本信息
	 * @param desc 描述信息
	 * @return
	 */
	Long saveItem(Item item, String desc);

	/**
	 * 更新商品基本和描述信息
	 * @param item 基本信息
	 * @param desc 描述信息
	 * @return
	 */
	void updateItem(Item item, String desc);

}
