package cn.itcast.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.pojo.User;

public class UserMapperTest {
	
	private UserMapper userMapper;
	
	@Before
	public void setup() throws IOException {
		//mybatis总配置文件
		String resource = "mybatis-config.xml";
		
		//利用mybatis提供的Resources工具类读取到总配置文件的输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//使用sqlSessionFactoryBuider创建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		userMapper  = sqlSession.getMapper(UserMapper.class);
	}

	@Test
	public void testQueryUsers() {
		List<User> list = userMapper.queryUsers();
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testDeleteByIds() {
		Long[] ids = {8L, 9L};
		userMapper.deleteByIds(ids);
	}

}
