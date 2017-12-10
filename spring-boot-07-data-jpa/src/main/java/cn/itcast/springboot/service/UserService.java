package cn.itcast.springboot.service;

import java.util.List;

import cn.itcast.springboot.pojo.User;

public interface UserService {

	/**
	 * 根据用户名模糊查询并按照年龄降序排序
	 */
	public List<User> findByUserNameLikeOrderByAgeDesc(String userName);
	
	public User saveUser(User user);
}
