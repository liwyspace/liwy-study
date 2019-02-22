package com.liwy.study.spring.spring4.service.impl;

import com.liwy.study.spring.spring4.bean.User;
import com.liwy.study.spring.spring4.dao.IUserDao;
import com.liwy.study.spring.spring4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String sayHello() {
        return "Hello " + userDao.getUserName(1L);
    }

    @Override
    public void insertTowUser() throws IOException {
        User user = new User();
        user.setUsername("事务测试2");
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

        int i = 1/0;
    }


}
