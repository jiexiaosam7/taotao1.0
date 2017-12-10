package cn.itcast.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/fm")
@Controller
public class FreemarkerController {

	/**
	 * 如果在形参中指定了Map接口的数据类型，那么设置到该参数中的任何数据都将设置到返回的Model
	 * @param map
	 * @return
	 */
	@RequestMapping("/test1/{userId}")
	public String test1(@PathVariable("userId") Long userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("userName", "传智播客");
		
		//返回视图；必须要在classpath:/templates文件夹下存在；视图默认的后缀为.ftl
		return "user/list";
	}
	
	@RequestMapping("/test2")
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView("user/list");
		mv.addObject("userId", "123");
		mv.addObject("userName", "黑马");
		return mv;
	}
}
