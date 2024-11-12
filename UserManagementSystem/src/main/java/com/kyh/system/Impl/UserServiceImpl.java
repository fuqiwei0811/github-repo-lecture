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
	public int insert(User user) {
		return userMapper.insert(user);
	}

	/***
	 * IDによるユーザー削除
	 *
	 */
	@Override
	public int delete(int no) {
		return userMapper.delete(no);
	}

	/***
	 *
	 * ユーザーを更新する
	 */
	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	/***
	 * ページング検索
	 * */
	@Override
	public List<User> selectAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectAll();
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
	public int getCount() {
		return userMapper.getCount();
	}

	@Override
	public int checkExistenceByUserId(User user) {
		return userMapper.checkExistenceByUserId(user);
	}
}
