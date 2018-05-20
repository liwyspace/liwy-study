package com.liwy.project.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.liwy.project.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration({ "classpath:applicationContext.xml",
		"classpath:applicationContext-ehcache.xml" })
public class UserServiceTest {

	private static final transient Logger log = LoggerFactory
			.getLogger(UserServiceTest.class);

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@Test
	public void testUser() {
		List<User> users = userService.findAll();
		for (User user : users) {
			log.info(user.toString());
		}
	}

	@Test
	public void testTimestamp() throws InterruptedException {
		System.out.println("第一次调用：" + userService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + userService.getTimestamp("param"));
		Thread.sleep(4000);
		System.out.println("再过4秒之后调用：" + userService.getTimestamp("param"));
	}

	@Test
	public void testCache() {
		User user = new User();
		user.setUsername("liwy");
		user.setPassword("123456");
		user.setSalt("111");
		user.setLocked(false);
		user.setRoleIdsStr("1,2,3");
		user.setOrganizationId(1L);
		userService.createUser(user);
		User user2 = userService.findByUsername(user.getUsername());

		userService.findOne(user2.getId()); // 从数据库中读取数据
		userService.findOne(user2.getId()); // 从缓存中获取数据，所以不执行该方法体
		userService.deleteUser(user2.getId()); // 从数据库中删除数据，并删除缓存
		userService.findOne(user2.getId()); // 从数据库中获取数据...（缓存数据删除了，所以要重新获取，执行方法体）
	}

	@Test
	public void testPut() {
		User user = new User();
		user.setUsername("liwy");
		user.setPassword("123456");
		user.setSalt("111");
		user.setLocked(false);
		user.setRoleIdsStr("1,2,3");
		user.setOrganizationId(1L);
		userService.createUser(user);
		User user2 = userService.findByUsername(user.getUsername());

		user2.setUsername("liwy222");
		user2.setPassword("abcdef");

		userService.updateUser(user2); // 更新数据库，并将结果保存在缓存中
		User user3 = userService.findOne(user2.getId()); // 从缓存中读取数据
		log.info(user3.toString());

		user2.setUsername("liwy3333");
		user2.setPassword("6666666");
		userService.updateUser(user2); // 更新数据库，并将结果保存在缓存中
		User user4 = userService.findOne(user2.getId()); // 从缓存中读取数据
		log.info(user4.toString());

		userService.deleteUser(user2.getId()); // 从数据库中删除数据，并删除缓存
	}
}
