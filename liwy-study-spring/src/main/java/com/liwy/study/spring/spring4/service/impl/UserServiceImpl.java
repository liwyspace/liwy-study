package com.liwy.study.spring.spring4.service.impl;

import com.liwy.study.spring.spring4.dao.IUserDao;
import com.liwy.study.spring.spring4.service.IUserService;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao;
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String sayHello() {
        return "Hello " + userDao.getUserName();
    }
}
