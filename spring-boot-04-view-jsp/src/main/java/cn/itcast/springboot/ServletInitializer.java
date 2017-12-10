package cn.itcast.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 如果是需要使用jsp视图展示数据的话；需要添加一个类，该类必须要继承一个SpringBootServletInitializer类，重写configure
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//将其请求转到引导类处理
		return builder.sources(Application.class);
	}
	
}
