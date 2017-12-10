package cn.itcast.springboot.datasource;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//如果是一个spring boot的项目的话，使用junit测试则必须要加该注解
public class C3p0DataSourceTest {
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void testGetDataSource() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println("-----------------------------------------------");
		System.out.println(connection);
	}

}
