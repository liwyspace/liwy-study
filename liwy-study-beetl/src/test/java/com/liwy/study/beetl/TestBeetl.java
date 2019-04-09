package com.liwy.study.beetl;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * Created by liwy on 2017/4/9.
 */
public class TestBeetl {
    @Test
    public void testBeetlTemplate() {
        GroupTemplate gt = new GroupTemplate();
        Template tp = gt.getTemplate("beetlTemp.html");
        tp.binding("username","liwenyao");
        tp.renderTo(new PrintWriter(System.out));
    }
}
