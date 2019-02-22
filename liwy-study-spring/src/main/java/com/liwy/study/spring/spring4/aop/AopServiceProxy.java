package com.liwy.study.spring.spring4.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 11:51 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Service
public class AopServiceProxy {
    @Autowired
    @Qualifier("aopServiceTwoImpl")
    private AopService aopService;

    public void sayHello() {
        aopService.sayHello("Proxy");
    }
}
