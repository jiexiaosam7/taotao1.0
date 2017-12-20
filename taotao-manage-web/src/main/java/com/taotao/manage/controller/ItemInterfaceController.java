package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;

@RequestMapping("/item/interface")
@Controller
public class ItemInterfaceController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * 增加商品/保存商品
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item){
		try {
			//保存基本信息
			itemService.saveSelective(item);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
	
	/**
	 * 根据商id查询商品
	 */
	@RequestMapping(value="{/itemId}",method=RequestMethod.GET)
	public ResponseEntity<Item> queryItemById(@PathVariable("itemId") Integer id){
		try {
			Item item = itemService.queryById(id);
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 更新商品
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(Item item){
		try {
			itemService.updateSelective(item);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//放回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/*
	 *删除商品
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@RequestParam(value="ids",required=false )Long[] ids){
		try {
			itemService.deleteByIds(ids);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
}
