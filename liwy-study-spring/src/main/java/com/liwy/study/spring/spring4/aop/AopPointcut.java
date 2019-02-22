package com.liwy.study.spring.spring4.aop;

import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

/**
 * <b>名称：</b> Aop API的切面类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 15:38 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class AopPointcut extends NameMatchMethodPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // 设置单个方法匹配
        // this.setMappedName("delete");

        // 也可以用“ * ” 来做匹配符号
        // this.setMappedName("get*");

        // 设置多个方法匹配
        String[] methods = { "delete*", "printHello" };
        this.setMappedNames(methods);

        return super.matches(method, targetClass);
    }
}
