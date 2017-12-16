package com.taotao.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

}
