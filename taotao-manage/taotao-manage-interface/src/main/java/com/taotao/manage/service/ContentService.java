package com.taotao.manage.service;

import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.pojo.Content;

public interface ContentService extends BaseService<Content> {

	/**
	 * 根据内容分类id查询该分类的分页内容列表
	 * @param categoryId 分类id
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows);
	
	/**
	 * 获取门户系统首页的大广告数据
	 * @return
	 * @throws Exception 
	 */
	String queryPortalBigAdData() throws Exception;

}
