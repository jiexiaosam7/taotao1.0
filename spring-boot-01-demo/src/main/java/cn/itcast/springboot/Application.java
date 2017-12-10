package cn.itcast.springboot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cn.itcast.springboot.properties.ItcastProperties;

/**
 * @SpringBootApplication 组合注解表示是一个spring boot 引导类；以后的请求处理由此开始；
 * 默认扫描本包及其所有的子包的注解
 * 
 * @EnableConfigurationProperties 当有自定义的配置类的时候添加
 */

@SpringBootApplication
@EnableConfigurationProperties(ItcastProperties.class)
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		//启动时不希望出现spring banner
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setBannerMode(Mode.OFF);//关闭banner
		springApplication.run(args);
	}

}
