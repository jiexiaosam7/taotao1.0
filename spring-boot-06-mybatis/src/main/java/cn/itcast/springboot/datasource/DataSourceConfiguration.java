package cn.itcast.springboot.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfiguration  {
	
	/**
	 * 注册一个DataSource数据源；名称为dataSource
	 * 如果有多个数据源的时候，以这个数据源为主
	 * @ConfigurationProperties(prefix="druid") 加载application.properties文件中的以c3p0为前缀的配置项
	 */
	@Bean("dataSource")
	@Primary //如果有多个数据源的时候，以这个为主
	@ConfigurationProperties(prefix="druid")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}

}
