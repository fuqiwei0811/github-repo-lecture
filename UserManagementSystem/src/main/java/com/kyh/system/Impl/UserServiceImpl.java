package com.kyh.system.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kyh.system.mapper.UserMapper;
import com.kyh.system.model.User;
import com.kyh.system.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	// データベース操作のマッパーをインジェクションする
	@Autowired
	private UserMapper userMapper;

	/**
	 * ユーザーを追加する
	 * */
	@Override
	public int addUser(User user) {
		return userMapper.insertSelective(user);
	}

	/***
	 * ページング検索
	 * */
	@Override
	public List<User> findAllUser(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectAllUser();
	}

	/***
	 * IDによるユーザー削除
	 *
	 */
	@Override
	public int delete(int no) {
		return userMapper.deleteByPrimaryKey(no);
	}

	/***
	 *
	 * ユーザーを更新する
	 */
	@Override
	public int update(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	* ユーザーログイン
	* */
	@Override
	public User getUserByUserIdAndPassword(User user) {
		return userMapper.selectByUserIdAndPassword(user);
	}

	@Override
	public User getUserByPrimaryKey(int no) {
		return userMapper.selectByPrimaryKey(no);
	}

	@Override
	public int getUsernumber() {
		return userMapper.getUsernumber();
	}

	@Override
	public int checkExistenceByUserId(User user) {
		return userMapper.checkExistenceByUserId(user);
	}
}
