package cn.itcast.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties 可以读取application.properties配置文件中的配置项
 * prefix 在application.properties配置文件中的配置项的前缀
 * ignoreUnknownFields 如果在application.properties配置文件中的配置项在本类中没有对应的属性的话则忽略
 */
@ConfigurationProperties(prefix="cn.itcast", ignoreUnknownFields=true)
public class ItcastProperties {

	private String name;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
