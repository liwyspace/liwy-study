package com.liwy.study.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * Created by liwy on 2017/4/8.
 */
public class FreemarkerTest {
    /**
     * Freemarker与web容器没有关系，
     * 普通的java项目也可以使用freemarker
     */
    @Test
    public void testFreemarker() throws IOException, TemplateException {
        //1.创建Freemarker配置实例
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("template").getPath()));
        //2.创建数据模型
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("username","liwy");
        model.put("random",new Random().nextInt(100));
        List<User> list= new ArrayList<User>();
        list.add(new User(1,"liwy","123456"));
        list.add(new User(2,"liwy2","1234562"));
        model.put("userList", list);
        //3. 加载模板文件
        Template template = configuration.getTemplate("index.ftl");
        //4. 显示生成的数据
        Writer out = new OutputStreamWriter(System.out);
        template.process(model,out);
        out.flush();
        out.close();
    }

    @Test
    public void testFreemarkerTwo() throws IOException, TemplateException {
        Configuration conf = Configuration.getDefaultConfiguration();
        conf.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("template").getPath()));
        Template template = conf.getTemplate("article.ftl");
        Map map = new HashMap();
        map.put("webSite","开源小屋");
        map.put("username","");
        List list = new ArrayList();
        list.add("首页");
        list.add("文章");
        list.add("作品");
        map.put("navers",list);
        Map armap = new HashMap();
        armap.put("title","标题111111");
        armap.put("context","skdjfsjfksdjf上岛咖啡结束了登记方式离开的房间施蒂利克废旧塑料");
        map.put("article",armap);
        Writer out = new OutputStreamWriter(System.out);
        template.process(map,out);
    }
}


