package com.liwy.study.spring.spring4;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <b>名称：</b> Resource接口测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ResourceApiTest {

    /**
     * <b>描述：</b> 测试Resources接口<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testResources() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("spring4/applicationContext-bean.xml"); // 从类路径上加载资源
        System.out.println(classPathResource.exists());
        System.out.println(classPathResource.getPath());
        System.out.println(classPathResource.getFilename());
        System.out.println(classPathResource.getURL());
        System.out.println(classPathResource.getFile());

        FileSystemResource fileSystemResource = new FileSystemResource(classPathResource.getFile()); // 从文件系统中获取资源
        System.out.println(fileSystemResource.exists());
        System.out.println(fileSystemResource.getPath());
        System.out.println(fileSystemResource.getFilename());
        System.out.println(fileSystemResource.getURL());
        System.out.println(fileSystemResource.getFile());

        InputStreamResource inputStreamResource = new InputStreamResource(classPathResource.getInputStream()); // InputStream 提供的 Resource 实现
        System.out.println(inputStreamResource.exists());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamResource.getInputStream()));
        System.out.println(reader.readLine());

        InputStream inputStream = classPathResource.getInputStream();
        byte[] buff = new byte[(int) classPathResource.contentLength()];
        inputStream.read(buff);
        ByteArrayResource byteArrayResource = new ByteArrayResource(buff); // 针对字节数组提供的 Resource 实现
        System.out.println(byteArrayResource.exists());
        System.out.println((char) byteArrayResource.getByteArray()[0]);
        System.out.println((char) byteArrayResource.getByteArray()[1]);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayResource.getByteArray())));
        System.out.println(reader1.readLine());

        UrlResource urlResource = new UrlResource("http://www.baidu.com"); // UrlResource 封装了一个 java.net.URL 对象，用来访问 URL 可以正常访问的任意对象，比如文件、an HTTP target, an FTP target, 等等。
        System.out.println(urlResource.exists());
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(urlResource.getInputStream()));
        System.out.println(reader2.readLine());

        //        ServletContextResource servletContextResource = new ServletContextResource(); // 从web根目录获取资源
    }

    /**
     * <b>描述：</b> 测试ResourceLoader接口<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * 
     * @param 
     * @return void
     */
    @Test
    public void testResourceLoader() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        Resource resource = applicationContext.getResource("spring4/applicationContext-bean.xml"); // 由ApplicationContext的类型决定从哪加载
        System.out.println(resource.exists());
        Resource resource1 = applicationContext.getResource("classpath:spring4/applicationContext-bean.xml"); // 从classpath下加载
        System.out.println(resource1.exists());
        Resource resource2 = applicationContext.getResource("file://D:/WorkSpace/GithubProjects/liwy-study/liwy-study-spring/src/test/resources/spring4/applicationContext-bean.xml"); // 从文件系统中加载
        System.out.println(resource2.exists());
        Resource resource3 = applicationContext.getResource("http://www.baidu.com"); // 从网络加载
        System.out.println(resource3.exists());
    }
    
    /**
     * <b>描述：</b> 测试应用上下文<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * 
     * @param 
     * @return void
     */
    @Test
    public void testApplicationContext() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring4/applicationContext-bean.xml"); //从类路径加载
        ApplicationContext ctx1 = new FileSystemXmlApplicationContext("D:/WorkSpace/GithubProjects/liwy-study/liwy-study-spring/src/test/resources/spring4/applicationContext-bean.xml"); // 从文件系统中加载
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext("classpath:spring4/applicationContext-bean.xml"); // 从类路径中加载，若位置路径带有 classpath 前缀或 URL 前缀，会覆盖默认创建的用于加载 bean 定义的 Resource 类型。

        // Ant 通配符风格模式
        ApplicationContext ctx3 = new ClassPathXmlApplicationContext("classpath:spring4/**/applicationContext*.xml"); //从类路径加载
        ApplicationContext ctx4 = new ClassPathXmlApplicationContext("classpath*:spring4/applicationContext.xml"); // classpath*：的使用表示类路径下所有匹配文件名称的资源都会被获取。
    }
}
