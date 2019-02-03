package com.liwy.study.spring.spring4.bean;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 14:37 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Service
public class AnnotationServiceBean {

    /**
     * <b>描述：</b> 初始化回调<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @PostConstruct
    private void init() {
        System.out.println("初始化回调");
    }

    /**
     * <b>描述：</b> 销毁回调<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @PreDestroy
    private void destroy() {
        System.out.println("析构回调");
    }
}
