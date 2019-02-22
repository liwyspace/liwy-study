package com.liwy.study.spring.spring4.aop;

import org.springframework.stereotype.Service;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 11:12 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Service
public class AopServiceOneImpl implements AopService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello "+ name +"!");
    }

    @Override
    public String printHello(String name) {
        System.out.println("AopServiceOneImpl.printHello");
        return "Hello "+ name +"!";
    }

    @Override
    public void sayHelloThrow(String name) throws Exception {
        System.out.println("AopServiceOneImpl.sayHelloThrow");
        throw new Exception("Hello "+ name +"!");
    }
}
