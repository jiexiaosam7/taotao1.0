package com.taotao.manage.service;

import com.taotao.common.vo.DataGridResult;
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

	/**
	 * 根据商品标题模糊分页查询并按照更新时间降序排序列表
	 * @param title 商品标题
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	DataGridResult queryItemListByPage(String title, Integer page, Integer rows);

}
