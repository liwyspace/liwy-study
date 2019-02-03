package com.liwy.study.spring.spring4.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 14:37 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Service("annotationBean")
public class AnnotationBean {
    /**
     * Autowired可在构造方法、setter方法、和字段上
     * 默认安装类型装配
     */
    @Autowired
    private AnnotationComponentBean annotationComponentBean;
    /**
     * 由于Autowired是基于类型的注入，所有有可能存在多个实例
     *
     */
    @Autowired
    @Qualifier("annotationControllerBean")
    private AnnotationControllerBean annotationControllerBean;

    /**
     * Resource可在字段和setter方法上
     * 默认按照名称装配
     *
     * Resource装配顺序：
     * 1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
     * 2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
     * 3. 如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
     * 4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。
     *
     */
    @Resource
    private AnnotationServiceBean annotationServiceBean;

    @Resource(name = "annotationRepositoryBean")
    private AnnotationRepositoryBean annotationRepositoryBean;

    @Override
    public String toString() {
        return "AnnotationBean{" +
                "annotationComponentBean=" + annotationComponentBean +
                ", annotationControllerBean=" + annotationControllerBean +
                ", annotationServiceBean=" + annotationServiceBean +
                ", annotationRepositoryBean=" + annotationRepositoryBean +
                '}';
    }
}
