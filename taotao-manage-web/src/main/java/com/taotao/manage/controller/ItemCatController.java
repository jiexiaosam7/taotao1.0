package com.taotao.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;

@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 根据商品类目父id查询该类目下的所有子类目；并在树组件加载数据
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value = "id", defaultValue="0")Long parentId){
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			List<ItemCat> list = itemCatService.queryByWhere(itemCat);
			
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果发生错误，异常；返回http响应状态码为500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	/**
	 * 分页查询商品类目列表
	 * ResponseEntity :可以将返回到内容作为输出内容并且还可以指定http响应状态码
	 * @param pageNo 页号
	 * @param rows 页大小
	 * @return
	 */
	@RequestMapping(value = "/query/{pageNo}", method = RequestMethod.GET)
	//@ResponseBody
	public ResponseEntity<List<ItemCat>> queryItemCatListInPage(@PathVariable Integer pageNo, @RequestParam(value = "rows", defaultValue = "20")Integer rows){
		try {
			//List<ItemCat> list = itemCatService.queryItemCatListInPage(pageNo, rows);
			List<ItemCat> list = itemCatService.queryByPage(pageNo, rows);
			
			//如果处理成功， 设置http响应状态码为200
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果发生错误，异常；返回http响应状态码为500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
