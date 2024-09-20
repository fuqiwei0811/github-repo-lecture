package com.kyh.system.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kyh.system.entity.User;
import com.kyh.system.mapper.UserMapper;
import com.kyh.system.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    // データベース操作のマッパーをインジェクションする
    @Autowired
    private UserMapper userMapper; // コンパイルエラーが発生する場合がありますが、影響はありません。メソッドを削除し、settingsのspring beanで設定できます

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
    public int delete(int key) {
        return userMapper.deleteByPrimaryKey(key);
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
    public User getUserById(User user) {
        return userMapper.selectByKeyAndPassword(user);
    }

    @Override
    public int getUsernumber() {
        return userMapper.getUsernumber();
    }

}
