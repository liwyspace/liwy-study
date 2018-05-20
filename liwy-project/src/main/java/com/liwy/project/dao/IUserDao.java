package com.liwy.project.dao;

import java.util.List;

import com.liwy.project.entity.User;

public interface IUserDao {
	public User createUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);
}
