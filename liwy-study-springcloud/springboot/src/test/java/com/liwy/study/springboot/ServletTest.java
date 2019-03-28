package com.liwy.study.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/5 10:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServletTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomeIsSecure() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> entity = this.restTemplate.exchange("/myservlet", HttpMethod.GET,
                new HttpEntity<Void>(headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testHome() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/myservlet", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }

    private String getPassword() {
        return "password";
    }
}
