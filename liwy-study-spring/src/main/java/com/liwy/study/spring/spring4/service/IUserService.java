package com.liwy.study.spring.spring4.service;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface IUserService {
    String sayHello();

    void insertTowUser() throws Exception;
}