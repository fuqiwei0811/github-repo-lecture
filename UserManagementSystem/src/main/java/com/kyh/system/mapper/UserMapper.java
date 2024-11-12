package com.kyh.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.kyh.system.model.User;

@Component
@Mapper
public interface UserMapper {

	User selectByPrimaryKey(Integer no);

	User selectByUserIdAndPassword(User user);
	
	int checkExistenceByUserId(User user);
	
	int delete(Integer no);

	int insert(User user);

	int update(User user);

	@Select("select count(*) from t_user")
	int getCount();

	@Select("select * from t_user")
	List<User> selectAll();

}