package com.liwy.study.spring.spring4.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liwy.study.spring.spring4.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/22 10:31 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration //调用Java Web组件，如自动注入ServletContext Bean等
@ContextConfiguration({"/spring4mvc/applicationContext.xml", "/spring4mvc/spring-mvc.xml"})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetUserPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/group/5/users/8")
                .param("sex", "男")
                .param("type", "童装"))
                .andDo(MockMvcResultHandlers.print(System.out))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/user.jsp"));
    }

    @Test
    public void testGetUser() throws Exception {
        MvcResult result = this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/user/usersjson/1")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(MockMvcResultHandlers.print(System.out)) //打印返回值
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.content().contentType(
                                "application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("email").value(
                                "email@121.com"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testAddUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setId(55L);
        user.setUsername("MVC Mock Test");
        System.out.println(mapper.writeValueAsString(user));

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/user/users")
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(mapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print(System.out)) //打印返回值
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFreemarker() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/free/index"))
                .andDo(MockMvcResultHandlers.print(System.out))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
