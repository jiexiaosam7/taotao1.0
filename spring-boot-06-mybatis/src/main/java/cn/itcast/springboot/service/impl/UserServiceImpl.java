package cn.itcast.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.springboot.mapper.UserMapper;
import cn.itcast.springboot.pojo.User;
import cn.itcast.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public List<User> queryUsersByUserName(String userName) {
		return userMapper.queryUsersByUserName(userName);
	}

	public List<User> queryUsersAll() {
		return userMapper.queryUsers();
	}

}
