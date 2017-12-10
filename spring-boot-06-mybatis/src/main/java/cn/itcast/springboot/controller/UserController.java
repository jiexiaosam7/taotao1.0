package cn.itcast.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.springboot.pojo.User;
import cn.itcast.springboot.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list/{userName}")
	public List<User> queryUsersByUserName(@PathVariable String userName){
		return userService.queryUsersByUserName(userName);
	}
	
	@RequestMapping("/list/all")
	public List<User> queryUsersAll(){
		return userService.queryUsersAll();
	}

}
