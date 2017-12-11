package cn.itcast.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.mybatis.pojo.User;

public interface UserMapper {

	//查询所有用户列表
	public List<User> queryUsers();
	
	//批量删除
	public void deleteByIds(@Param("ids")Long[] ids);
}
