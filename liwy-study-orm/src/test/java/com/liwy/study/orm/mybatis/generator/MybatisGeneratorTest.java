package com.liwy.study.orm.mybatis.generator;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * <b>名称：</b> 测试mybatis代码生成器<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 17:31 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class MybatisGeneratorTest {

    @AfterClass
    public static void after() {
        System.out.println(MybatisGeneratorTest.class.getResource("/"));
    }

    @Test
    public void test() {
        MyBatisGeneratorUtils.generator();
    }
}
