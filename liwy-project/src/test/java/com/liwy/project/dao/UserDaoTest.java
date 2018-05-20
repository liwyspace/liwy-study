package com.liwy.project.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.liwy.project.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@Transactional
public class UserDaoTest {

	@Autowired
	private IUserDao userDao;

	@Test
	public void testCreateUser() {
		User user = new User("liwy4", "123");
		user.setOrganizationId(1L);
		user.setRoleIdsStr("1,2,3");
		user.setSalt("111");
		userDao.createUser(user);
	}
}
