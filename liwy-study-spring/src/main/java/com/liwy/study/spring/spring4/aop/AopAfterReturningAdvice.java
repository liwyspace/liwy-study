package com.liwy.study.spring.spring4.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * <b>名称：</b> 后置通知<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 15:44 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class AopAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("返回值："+o);
        System.out.println("方法：" + method);
        System.out.println("参数："+objects);
        System.out.println("对象："+o1);
        System.out.println("进入后置通知");
    }
}
