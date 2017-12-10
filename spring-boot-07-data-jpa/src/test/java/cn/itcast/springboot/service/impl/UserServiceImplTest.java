package cn.itcast.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.springboot.pojo.User;
import cn.itcast.springboot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testFindByUserNameLikeOrderByAgeDesc() {
		List<User> list = userService.findByUserNameLikeOrderByAgeDesc("%雷%");
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setId(8L);
		user.setAccount("heima");
		user.setAge(11);
		user.setBirthday(new Date());
		user.setUserName("黑马2");
		user.setGender(1);
		
		User user2 = userService.saveUser(user);
		
		System.out.println(user2);
	}

}
