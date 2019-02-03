package com.liwy.study.spring.spring4.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 15:17 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Configuration
@ComponentScan(basePackages = "com.liwy.study.spring.spring4.bean")
//@ImportResource("classpath:/com/acme/application-config.xml")  // 以@Configuration类为主引入XML
//@Profile("development")  // 设置应用环境，也可在@Bean的方法上
@PropertySource("classpath:config-jdbc.properties")
public class JavaContext {

    @Autowired
    Environment env;

    @Bean
    public PropertyBean propertyBean() {
        return new PropertyBean();
    }

    @Bean
    public InstansBean instansBean() {
        return new InstansBean(env.getProperty("db_username"));
    }

    @Bean
    public IocBean iocBean(InstansBean instansBean) {
        IocBean iocBean = new IocBean();
        iocBean.setName("yaoyao");
        iocBean.setInstansBean(instansBean);
        return iocBean;
    }
}
