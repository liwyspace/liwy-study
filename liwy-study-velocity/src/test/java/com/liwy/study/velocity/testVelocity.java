package com.liwy.study.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by liwy on 2017/4/9.
 */
public class testVelocity {
    @Test
    public void testVelocityTemplate() throws IOException {
        Velocity.init("velocity.properties");
        VelocityContext context = new VelocityContext();
        context.put("username","liwenyao");
        Writer out = new PrintWriter(System.out);
        Velocity.mergeTemplate("index.vm","utf-8",context,out);
        out.flush();
        out.close();
    }
}
