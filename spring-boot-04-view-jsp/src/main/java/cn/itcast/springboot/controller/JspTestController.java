package cn.itcast.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/jsp")
@Controller
public class JspTestController {

	@RequestMapping("/test")
	public ModelAndView test() {
		//视图需要根据application.properties中配置的路径下存在
		ModelAndView mv = new ModelAndView("test");
		
		mv.addObject("msg", "数据来自后台...");
		
		return mv;
	}
}
