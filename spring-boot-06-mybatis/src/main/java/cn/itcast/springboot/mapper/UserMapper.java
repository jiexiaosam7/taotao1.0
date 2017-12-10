package cn.itcast.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.itcast.springboot.pojo.User;

@Mapper
public interface UserMapper {
	
	//使用注解方式模糊查询
	@Select("select * from tb_user where user_name like '%${userName}%'")
	public List<User> queryUsersByUserName(@Param("userName")String userName);

	//查询所有
	public List<User> queryUsers();
}
