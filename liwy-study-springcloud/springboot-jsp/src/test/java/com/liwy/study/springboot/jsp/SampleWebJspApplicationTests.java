package com.liwy.study.springboot.jsp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/5 10:36 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleWebJspApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testJspWithEl() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("/resources/text.txt");
    }

    @Test
    public void customErrorPage() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET,
                URI.create("/foo"));
        ResponseEntity<String> entity = this.restTemplate.exchange(request, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(entity.getBody())
                .contains("Something went wrong: 500 Internal Server Error");
    }
}
