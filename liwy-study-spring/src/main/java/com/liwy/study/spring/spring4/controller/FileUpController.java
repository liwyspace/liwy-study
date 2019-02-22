package com.liwy.study.spring.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/20 17:07 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/file")
public class FileUpController {
    @PostMapping("/form")
    @ResponseBody
    public String handleFormUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(name);
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
//            System.out.println(new String(bytes));

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            return "上传成功";
        }
        return "未上传";
    }
}
