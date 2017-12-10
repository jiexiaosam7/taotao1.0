package cn.itcast.springboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.springboot.dao.UserRepository;
import cn.itcast.springboot.pojo.User;
import cn.itcast.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> findByUserNameLikeOrderByAgeDesc(String userName) {
		return userRepository.findByUserNameLikeOrderByAgeDesc(userName);
	}

	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
