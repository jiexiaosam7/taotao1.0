package com.taotao.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;
import com.taotao.manage.service.redis.JedisService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	@Value("${PORTAL_INDEX_BIG_ADD_NUMBER}")
	private Integer PORTAL_INDEX_BIG_ADD_NUMBER;

	@Value("${CONTENT_CATEGORY_BIG_AD_ID}")
	private Long CONTENT_CATEGORY_BIG_AD_ID;

	@Autowired
	private ContentMapper contentMapper;

	private static final ObjectMapper mapper = new ObjectMapper();//
	
	@Autowired
	private JedisService jedisService;
	
	//首页大广告数据存储在redis中的key的名称
		private static final String REDIS_BIG_AD_KEY = "PORTAL_INDEX_BIG_AD_DATA";

		//首页大广告数据存储在redis中的数据的过期时间；1天
		private static final int REDIS_BIG_AD_EXPIRE_TIME = 60*60*24;

	@Override
	public DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows) {
		// 根据内容分类id分页查询该分类下的内容列表并根据更新时间降序排序
		Example example = new Example(Content.class);

		// 设置查询条件
		example.createCriteria().andEqualTo("categoryId", categoryId);

		// 设置排序
		example.orderBy("updated").desc();

		// 设置分页
		PageHelper.startPage(page, rows);

		List<Content> list = contentMapper.selectByExample(example);

		PageInfo<Content> pageInfo = new PageInfo<>(list);
		return new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
	}

	
	
	@Override
	public String queryPortalBigAdData() throws Exception{
		
		//注意:如果出现异常catch,不影响使用
		try {
			//先从缓存中查找数据
			String jsonStr = jedisService.get(REDIS_BIG_AD_KEY);
			//如果从缓存中找到数据则直接返回
			if (StringUtils.isNotBlank(jsonStr)) {
				return jsonStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//1、查询6条最新的大广告数据
				DataGridResult dataGridResult = queryContentListByPage(CONTENT_CATEGORY_BIG_AD_ID, 1,
						PORTAL_INDEX_BIG_ADD_NUMBER);
				//2、将6条大广告数据转换为符合格式的字符串
				List<Content> contentList = (List<Content>) dataGridResult.getRows();

				List<Map<String, Object>> resultList = new ArrayList<>();

				Map<String, Object> map = null;

				if (contentList != null && contentList.size()>0) {
					for (Content c : contentList) {
						map = new HashMap<>();
						map.put("alt", c.getTitle());
						map.put("height", 240);
						map.put("heightB", 240);
						map.put("href", c.getUrl());
						map.put("src", c.getPic());
						map.put("srcB", c.getPic());
						map.put("width", 670);
						map.put("widthB", 550);
						resultList.add(map);
					}
				}
				//转为字符串
				String str = mapper.writeValueAsString(resultList);
				try {
					//将数据写入缓存，过期时间为1天
					jedisService.setex(REDIS_BIG_AD_KEY, REDIS_BIG_AD_EXPIRE_TIME, str);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return str;
	}

}
