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
@Aspect
public class MyAspectJ {
    ///////////// 定义切入点
    // 任意公共方法的执行
    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {
    }

    //任何一个名字以“set”开始的方法的执行
    @Pointcut("execution(* set*(..))")
    private void anSetOperation() {
    }

    //AopService类的任意方法执行
    @Pointcut("execution(* com.liwy.study.spring.spring4.aop.AopService.*(..))")
    private void aopServiceAnyOperation() {
    }

    //在aop包中定义的任意方法的执行
    @Pointcut("execution(* com.liwy.study.spring.spring4.aop.*.*(..))")
    private void aopPackageAnyOperation() {
    }

    //在spring4包或其子包中定义的任意方法的执行
    @Pointcut("execution(* com.liwy.study.spring.spring4..*.*(..))")
    private void spring4PackageAndSubAnyOperation() {
    }

    //在service包或其子包中的任意连接点（在Spring AOP中只是方法执行）,通常与execution切入点组合使用
    @Pointcut("within(com.liwy.study.spring.spring4..*)")
    private void inTrading() {
    }

    // 代理对象引用的任意连接点 （在Spring AOP中只是方法执行）,通常与execution切入点组合使用
    @Pointcut("this(com.liwy.study.spring.spring4.aop.AopServiceProxy)")
    private void thisTrading() {
    }

    // 目标对象的任意连接点 （在Spring AOP中只是方法执行）,通常与execution切入点组合使用
    @Pointcut("target(com.liwy.study.spring.spring4.aop.AopServiceOneImpl)")
    private void targetTrading() {
    }

    // 组合切入点
    @Pointcut("anyPublicOperation() && inTrading()")
    private void inOperation() {
    }

    @Pointcut("anyPublicOperation() && thisTrading()")
    private void thisOperation() {
    }

    @Pointcut("anyPublicOperation() && targetTrading()")
    private void targetOperation() {
    }

    ///////////// 定义切入点
    // 前置通知
    @Before(value = "aopServiceAnyOperation()") // 使用已声明的切入点
    public void before() {
        System.out.println("前置通知....");
    }

    // 后置通知 returnVal,切点方法执行后的返回值
    @AfterReturning(value = "execution(* com.liwy.study.spring.spring4.aop.AopService.printHello(..))", returning = "returnVal")
    // 自定义切入点并接受返回值
    public void AfterReturning(Object returnVal) {
        System.out.println("后置通知...." + returnVal);
    }

    // 环绕通知
    @Around("execution(* com.liwy.study.spring.spring4.aop.AopService.sayHello(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知前....");
        Object obj = (Object) joinPoint.proceed();
        System.out.println("环绕通知后....");
        return obj;
    }

    // 抛出异常通知
    @AfterThrowing(value = "execution(* com.liwy.study.spring.spring4.aop.AopService.sayHelloThrow(..))", throwing = "e")
    public void afterThrowable(Throwable e) {
        System.out.println("出现异常:msg=" + e.getMessage());
    }

    // 无论什么情况下都会执行的方法
    @After(value = "aopServiceAnyOperation()")
    public void after() {
        System.out.println("最终通知....");
    }

    // 前置通知
    @Before(value = "inOperation()")
    public void before2() {
        System.out.println("within前置通知....");
    }

    // 前置通知
    @Before(value = "thisOperation()")
    public void before3() {
        System.out.println("this前置通知....");
    }

    // 前置通知
    @Before(value = "targetOperation()")
    public void before4() {
        System.out.println("target前置通知....");
    }
}
