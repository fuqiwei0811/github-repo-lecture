package com.kyh.system.service;

import java.util.List;

import com.kyh.system.entity.User;

public interface UserService {

	int addUser(User user);

	List<User> findAllUser(int pageNum, int pageSize);

	int delete(int no);

	int update(User user);

	public User getUserByUserIdAndPassword(User user);
	
	public int checkExistenceByUserId(User user);

	public User getUserByPrimaryKey(int no);

	public int getUsernumber();
}
