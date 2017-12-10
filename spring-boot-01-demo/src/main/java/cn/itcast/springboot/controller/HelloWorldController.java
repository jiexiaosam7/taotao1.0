package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.springboot.properties.ItcastProperties;

@RequestMapping("/hello")
@RestController //组合注解包含了@Controller 及 @ResponseBody；对本类的所有方法的返回值作为输出内容
public class HelloWorldController {
	
	//第1种读取方式：
	@Autowired
	private Environment environment;
	//第2种读取方式：
	@Value("${cn.itcast.name}")
	private String itcastName;
	@Value("${cn.itcast.url}")
	private String itcastUrl;
	@Value("${com.itheima.name}")
	private String heimaName;
	
	//第3种读取方式：
	@Autowired
	private ItcastProperties itcastProperties;
	
	
	@RequestMapping("/test2")
	public String sayHello() {
		
		System.out.println("第1种读取配置文件------------------------");
		System.out.println("cn.itcast.name:" + environment.getProperty("cn.itcast.name"));
		System.out.println("cn.itcast.url:" + environment.getProperty("cn.itcast.url"));
		System.out.println("com.itheima.name:" + environment.getProperty("com.itheima.name"));
		System.out.println("第2种读取配置文件------------------------");
		System.out.println("cn.itcast.name:" + itcastName);
		System.out.println("cn.itcast.url:" + itcastUrl);
		System.out.println("com.itheima.name:" + heimaName);

		System.out.println("第3种读取配置文件------------------------");
		System.out.println("cn.itcast.name:" + itcastProperties.getName());
		System.out.println("cn.itcast.url:" + itcastProperties.getUrl());

		return "Hello Spring Boot.";
	}
}
