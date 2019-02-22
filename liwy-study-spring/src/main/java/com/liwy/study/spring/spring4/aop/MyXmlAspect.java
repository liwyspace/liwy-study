package com.liwy.study.spring.spring4.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <b>名称：</b> AspectJ切面类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 10:43 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class MyXmlAspect {
    public void before() {
        System.out.println("XML前置通知....");
    }

    public void afterReturning(Object returnVal) {
        System.out.println("XML后置通知...." + returnVal);
    }

    public Object around(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        System.out.println("XML环绕通知前....");
        System.out.println("参数name:" + name);
        Object obj = (Object) joinPoint.proceed();
        System.out.println("XML环绕通知后....");
        return obj;
    }

    public void afterThrowable(Throwable e) {
        System.out.println("XML出现异常:msg=" + e.getMessage());
    }

    public void after() {
        System.out.println("XML最终通知....");
    }
}
