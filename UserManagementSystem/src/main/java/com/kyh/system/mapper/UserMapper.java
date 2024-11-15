package com.kyh.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kyh.system.model.User;
import com.kyh.system.model.UserExample;

public interface UserMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	long countByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int deleteByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer no);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int insert(User record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int insertSelective(User record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	List<User> selectByExample(UserExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	User selectByPrimaryKey(Integer no);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table t_user
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(User record);

	// すべてのユーザーを取得するクエリ
	// これは説明のために追加したメソッドで、実際のコードでは使用しません。
	@Select("SELECT * FROM t_user")
	List<User> getAllUsers();

	// ユーザーをnoで検索するクエリ
	//これは説明のために追加したメソッドで、実際のコードでは使用しません。
	// User selectByPrimaryKey(Integer no); と同じ
	@Select("SELECT * FROM t_user WHERE no = #{no}")
	User getUserById(@Param("no") Integer no);
}