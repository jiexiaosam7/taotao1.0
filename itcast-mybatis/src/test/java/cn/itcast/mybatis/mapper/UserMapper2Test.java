package cn.itcast.mybatis.mapper;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.mybatis.pojo.User2;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class UserMapper2Test {
	
	private UserMapper2 userMapper2;

	@Before
	public void setUp() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		//配置通用mapper
		MapperHelper mapperHelper = new MapperHelper();
		//特殊配置
		Config config = new Config();
		//设置配置
		mapperHelper.setConfig(config);
		// 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
		mapperHelper.registerMapper(Mapper.class);
		//配置完成后，执行下面的操作
		mapperHelper.processConfiguration(sqlSession.getConfiguration());

		
		userMapper2 = sqlSession.getMapper(UserMapper2.class);
		
	}

	@Test
	public void testSelectOne() {
		User2 param = new User2();
		param.setAccount("lilei");
		
		//返回的结果必须只能是一个
		User2 user2 = userMapper2.selectOne(param);
		System.out.println(user2);
	}

	@Test
	public void testSelectAll() {
		List<User2> list = userMapper2.selectAll();
		for (User2 user2 : list) {
			System.out.println(user2);
		}
	}

	@Test
	public void testSelectByPrimaryKey() {
		User2 user2 = userMapper2.selectByPrimaryKey(1L);
		System.out.println(user2);
	}

	@Test
	public void testInsertSelective() {
		User2 param = new User2();
		param.setAccount("itcast");
		param.setAge(1);
		param.setBirthday(new Date());
		param.setUserName("传智播客");
		param.setGender(1);
		
		userMapper2.insertSelective(param);
		
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		User2 param = new User2();
		param.setId(7L);
		param.setBirthday(new Date());
		param.setUserName("黑马");
		param.setGender(0);
		
		userMapper2.updateByPrimaryKeySelective(param);
	}

	@Test
	public void testDeleteByPrimaryKey() {
		userMapper2.deleteByPrimaryKey(7L);
	}

	/**
	 * 分页查询第2页的男性用户并根据年龄降序
	 */
	@Test
	public void testSelectByExample() {
		//创建查询对象
		Example example = new Example(User2.class);
		
		//设置分页；每页2条，查询第2页
		PageHelper.startPage(2,2);//页号，页大小
		
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("gender", 1);//男性用户
		
		//设置根据年龄降序排序
		example.orderBy("age").desc();
		
		List<User2> list = userMapper2.selectByExample(example);
		
		//转换为分页信息对象
		PageInfo<User2> pageInfo = new PageInfo<>(list);
		
		System.out.println("总记录数：" + pageInfo.getTotal() + "；总页数：" + pageInfo.getPages() + "；当前页号：" + 
				pageInfo.getPageNum() +"；页大小：" + pageInfo.getPageSize());
		
		for (User2 user2 : list) {
			System.out.println(user2);
		}
		
	}

}
