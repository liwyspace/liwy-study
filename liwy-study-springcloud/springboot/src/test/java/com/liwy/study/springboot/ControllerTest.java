package com.liwy.study.springboot;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/1 15:48 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(IndexController.class) // 单独加载配置的bean
@SpringBootTest(classes = Application.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 加载整个容器
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebClient webClient;
    @Autowired
    private WebDriver webDriver;

    @Test
    public void testApplicationContext() {
        assertThat(this.applicationContext.getBean(IndexController.class))
                .isNotNull();
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/index/first"))
                .andDo(MockMvcResultHandlers.print(System.out))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testJdbc() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/jdbc/mybatis"))
                .andDo(MockMvcResultHandlers.print(System.out))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
