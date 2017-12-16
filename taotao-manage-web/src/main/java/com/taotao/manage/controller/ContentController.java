package com.taotao.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.vo.DataGridResult;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;

@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 删除内容
	 * @param content 内容
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteContent(@RequestParam(value="ids", required = false)Long[] ids){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 500);
		try {
			if(ids != null && ids.length > 0) {
				contentService.deleteByIds(ids);
			}
			//contentService.deleteContent(content);
			result.put("status", 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回200
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 更新内容
	 * @param content 内容
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Void> updateContent(Content content){
		try {
			contentService.updateSelective(content);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 保存内容
	 * @param content 内容
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveContent(Content content){
		try {
			
			//Content cc = contentService.saveContent(content);
			contentService.saveSelective(content);
			
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 根据内容分类id查询该分类的分页内容列表
	 * @param categoryId 分类id
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<DataGridResult> queryContentListByPage(
			@RequestParam(value = "categoryId", defaultValue = "0")Long categoryId,
			@RequestParam(value="page", defaultValue="1")Integer page,
			@RequestParam(value = "rows",defaultValue="20")Integer rows){
		try {
			
			DataGridResult dataGridResult = contentService.queryContentListByPage(categoryId, page, rows);
			
			return ResponseEntity.ok(dataGridResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
