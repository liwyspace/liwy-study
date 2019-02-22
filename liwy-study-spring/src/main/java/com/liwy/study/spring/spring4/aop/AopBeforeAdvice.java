package com.liwy.study.spring.spring4.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * <b>名称：</b> 前置通知<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 15:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class AopBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("对象：" + o);
        System.out.println("方法：" + method);
        System.out.println("入参：" + objects);

        System.out.println("要进入切入点方法了");
    }
}
