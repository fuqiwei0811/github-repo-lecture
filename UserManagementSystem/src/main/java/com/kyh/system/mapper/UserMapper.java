package com.kyh.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.kyh.system.entity.User;

@Component
@Mapper
public interface UserMapper {

	User selectByPrimaryKey(Integer no);

	User selectByUserIdAndPassword(User user);
	
	int checkExistenceByUserId(User user);

	int getUsernumber();

	int deleteByPrimaryKey(Integer no);

	int insertSelective(User user);

	int updateByPrimaryKeySelective(User user);

	List<User> selectAllUser();

}