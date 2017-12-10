package cn.itcast.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.itcast.springboot.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 根据用户名模糊查询并按照年龄降序排序
	 */
	public List<User> findByUserNameLikeOrderByAgeDesc(String userName);
}
