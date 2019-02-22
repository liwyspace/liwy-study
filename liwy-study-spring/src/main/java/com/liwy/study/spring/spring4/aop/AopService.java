package com.liwy.study.spring.spring4.aop;

import org.springframework.stereotype.Service;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 10:56 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface AopService {
    void sayHello(String name);

    String printHello(String name);

    void sayHelloThrow(String name) throws Exception;
}
