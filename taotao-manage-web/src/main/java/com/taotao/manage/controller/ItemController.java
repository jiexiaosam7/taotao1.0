package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;

@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 更新商品基本和描述信息
	 * @param item 基本信息
	 * @param desc 描述信息
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateItem(Item item, @RequestParam(value="desc", required = false)String desc){
		
		try {
			itemService.updateItem(item, desc);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回状态码为500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	/**
	 * 保存商品基本和描述信息
	 * @param item 基本信息
	 * @param desc 描述信息
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item, @RequestParam(value="desc", required = false)String desc){
		
		try {
			Long itemId = itemService.saveItem(item, desc);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回状态码为500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
