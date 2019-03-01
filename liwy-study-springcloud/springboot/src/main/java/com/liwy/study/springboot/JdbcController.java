package com.liwy.study.springboot;

import com.liwy.study.springboot.dao.IUserDao;
import com.liwy.study.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/1 13:50 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Resource(name = "userDaoJdbcImpl")
    private IUserDao userDao;

    @Resource(name = "iUserDao")
    private IUserDao iUserDao;

    @RequestMapping("/")
    public String jdbc() {
        return String.valueOf(userDao.getUserCount());
    }

    @RequestMapping("/mybatis")
    public User mybatis() {
        return iUserDao.getUser(1L);
    }
}
