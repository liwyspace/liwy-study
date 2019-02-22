package com.liwy.study.spring.spring4;

import com.liwy.commons.lang.StringUtils;
import com.liwy.study.spring.spring4.bean.User;
import com.liwy.study.spring.spring4.dao.IUserDao;
import com.liwy.study.spring.spring4.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <b>名称：</b> 声明式事务与数据访问测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 16:27 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class DataTest {
    @Test
    public void testJdbcTemplate() throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-transaction.xml");
        IUserDao userDao = context.getBean("userDaoJdbcImpl", IUserDao.class);

        // 查询
        System.out.println(userDao.getUserCount());
        User user1 = userDao.getUser(200422L);
        System.out.println(user1);
        FileOutputStream outputStream = new FileOutputStream(new File("E://test1122bak.xml"));
        outputStream.write(user1.getIcon());
        outputStream.close();
        System.out.println(userDao.queryUserList().get(0));
        System.out.println(userDao.getUserName(1L));

        //插入数据
        User user = new User();
        user.setUsername("jdbcTemplate");
        user.setSex((byte) 1);
        user.setEmail("template@126.com");
        user.setStatus((byte) 1);
        user.setRegisterTime(new Date());
        user.setContent("正文，正文");
        InputStream inp = new FileInputStream(new File("E://test.xml"));
        byte[] byarr = new byte[inp.available()];
        inp.read(byarr);
        inp.close();
        user.setIcon(byarr);
        userDao.insertUser(user);

        //插入数据获取到自增主键
        User user6 = new User();
        user6.setUsername("jdbcTemplate");
        user6.setSex((byte) 1);
        user6.setEmail("template@126.com");
        user6.setStatus((byte) 1);
        user6.setRegisterTime(new Date());
        user6.setContent("正文，正文");
        InputStream inp6 = new FileInputStream(new File("E://test.xml"));
        byte[] byarr6 = new byte[inp6.available()];
        inp6.read(byarr);
        inp6.close();
        user6.setIcon(byarr6);
        userDao.insertUserGetId(user6);

        // 批量插入
        List<User> users2 = new ArrayList<>();
        User user13 = new User();
        user13.setUsername("批量插入11L");
        user13.setSex((byte) 1);
        user13.setEmail("templateUUU@126.com");
        user13.setRegisterTime(new Date());
        user13.setStatus((byte) 1);
        user13.setContent("正文，正文");
        InputStream inp1 = new FileInputStream(new File("E://test.xml"));
        byte[] byarr1 = new byte[inp1.available()];
        inp1.read(byarr1);
        inp1.close();
        user13.setIcon(byarr1);
        users2.add(user13);
        User user14 = new User();
        user14.setUsername("批量插入12L");
        user14.setSex((byte) 1);
        user14.setEmail("templateUUU@126.com");
        user14.setRegisterTime(new Date());
        user14.setStatus((byte) 1);
        user14.setContent("正文，正文");
        InputStream inp2 = new FileInputStream(new File("E://test.xml"));
        byte[] byarr2 = new byte[inp2.available()];
        inp2.read(byarr2);
        inp2.close();
        user14.setIcon(byarr2);
        users2.add(user14);
        userDao.batchInsert(users2);

        // 更新数据
        User user2 = new User();
        user2.setUsername("jdbcTemplateUUUU");
        user2.setSex((byte) 1);
        user2.setEmail("templateUUU@126.com");
        user2.setRegisterTime(new Date());
        user2.setId(200422L);
        userDao.updateUser(user2);

        // 批量更新
        List<User> users = new ArrayList<>();
        User user3 = new User();
        user3.setUsername("李文姚11L");
        user3.setSex((byte) 1);
        user3.setEmail("templateUUU@126.com");
        user3.setRegisterTime(new Date());
        user3.setId(11L);
        users.add(user3);
        User user4 = new User();
        user4.setUsername("李文姚12L");
        user4.setSex((byte) 1);
        user4.setEmail("templateUUU@126.com");
        user4.setRegisterTime(new Date());
        user4.setId(12L);
        users.add(user4);
        userDao.batchUserList(users);

        // 删除数据
        userDao.delete(100L);

        // 调用存储过程
        userDao.getEntityCount(1);
    }

    @Test
    public void testSimpleJdbc() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-transaction.xml");
        IUserDao userDao = context.getBean("userDaoJdbcImpl", IUserDao.class);

        //插入数据
        User user = new User();
        user.setUsername("SimpleJdbcInsert");
        user.setSex((byte) 1);
        user.setEmail("template@126.com");
        user.setStatus((byte) 1);
        user.setRegisterTime(new Date());
        user.setContent("正文，正文");
        InputStream inp = new FileInputStream(new File("E://test.xml"));
        byte[] byarr = new byte[inp.available()];
        inp.read(byarr);
        inp.close();
        user.setIcon(byarr);
        userDao.simpleInsertUser(user);

        // 调用存储过程
        userDao.simpleGetEntityCount(1000006);

        // 存储过程返回结果集
        userDao.simpleQueryChannelTag();
    }

    @Test
    public void testTransaction() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-transaction.xml");
        IUserService userService = context.getBean("userServiceImpl", IUserService.class);
        userService.insertTowUser();
    }
}
