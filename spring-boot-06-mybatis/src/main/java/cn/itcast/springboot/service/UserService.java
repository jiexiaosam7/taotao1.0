package cn.itcast.springboot.service;

import java.util.List;

import cn.itcast.springboot.pojo.User;

public interface UserService {

	/**
	 * 根据用户名模糊查询用户记录
	 * @param userName 用户名
	 * @return
	 */
	List<User> queryUsersByUserName(String userName);

	/**
	 * 查询所有用户记录
	 * @return
	 */
	List<User> queryUsersAll();

}
