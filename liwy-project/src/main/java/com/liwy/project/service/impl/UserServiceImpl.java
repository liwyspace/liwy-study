package com.liwy.project.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liwy.project.dao.IUserDao;
import com.liwy.project.entity.User;
import com.liwy.project.service.IRoleService;
import com.liwy.project.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static final transient Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleService roleService;

	/**
	 * 测试师傅有效，5s
	 */
	@Cacheable(value = "HelloWorldCache", key = "#param")
	@Override
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public User createUser(User user) {
		// 加密密码
		// passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	@CachePut(value = "UserCache", key = "'user:' + #user.id")
	@Override
	public User updateUser(User user) {
		log.info("更新数据库中数据");
		return userDao.updateUser(user);
	}

	/**
	 * 清除掉UserCache中某个指定key的缓存
	 */
	@CacheEvict(value = "UserCache", key = "'user:' + #userId")
	@Override
	public void deleteUser(Long userId) {
		log.info("删除数据库中数据");
		userDao.deleteUser(userId);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);
		// passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	@Cacheable(value = "UserCache", key = "'user:' + #userId")
	@Override
	public User findOne(Long userId) {
		log.info("从数据库中查找数据");
		return userDao.findOne(userId);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return Collections.EMPTY_SET;
		}
		return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return Collections.EMPTY_SET;
		}
		return roleService.findPermissions(user.getRoleIds().toArray(
				new Long[0]));
	}
}
