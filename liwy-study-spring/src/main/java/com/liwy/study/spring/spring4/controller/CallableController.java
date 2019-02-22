package com.liwy.study.spring.spring4.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <b>名称：</b> 异步请求<br/>
 * <b>描述：</b> 控制器方法现在不用像往常一样返回值，而是java.util.concurrent.Callable从Spring MVC托管线程返回 并产生返回值。
 * 同时主Servlet容器线程退出并释放，并允许处理其他请求。<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/20 10:48 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/async")
public class CallableController {
    @RequestMapping("/response-body")
    @ResponseBody
    public Callable<String> callable() {
        System.out.println("response-body======");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Callable result======";
            }
        };
    }

    @RequestMapping("/view")
    public Callable<String> callableWithView(Model model) {

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                model.addAttribute("foo", "bar");
                model.addAttribute("fruit", "apple");
                return "index";
            }
        };
    }

    @RequestMapping("/custom-timeout-handling")
    @ResponseBody
    public WebAsyncTask<String> callableWithCustomTimeoutHandling() {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Callable result";
            }
        };

        return new WebAsyncTask<String>(1000, callable);
    }

    @RequestMapping(path = "deferred", method = RequestMethod.GET)
    public DeferredResult<String> getReferred(@RequestParam(defaultValue = "0") long waitSec, Model model) {

        model.addAttribute("acceptedTime", LocalDateTime.now());

        // 超时时间为10s，超时返回"ERROR"。
        DeferredResult<String> deferredResult = new DeferredResult<String>((long) 10000, "ERROR");

        //要把该deferredResult的引用传给对应的异步函数处理
        this.asyncProcessing(model, waitSec, deferredResult);

        // 其他处理...
        System.out.println("其他处理。。。。。");

        return deferredResult; // 注意：返回值是该DeferredResult
    }

    @Async // 该注解十分方便，能使其变成异步函数（相当于一个线程的run函数）
    public void asyncProcessing(Model model, long waitSec, DeferredResult<String> deferredResult) {
        //要把该deferredResult的引用传给对应的异步函数处理
        try {
            TimeUnit.SECONDS.sleep(waitSec);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        model.addAttribute("completedTime", LocalDateTime.now());
        deferredResult.setResult("complete"); // 此时就通知MVC异步处理已经完成，可以生成HTTP响应。因此后面的代码不会造成HTTP响应的延迟
    }


    /**
     * <b>描述：</b> 返回二进制流，二进制流直接返回给客户端。这种方法可以用来向客户端发送图片等数据<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
     */
    @RequestMapping("/streamBody")
    public StreamingResponseBody streamBody() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("E://test_bak.gif");

        return (output) -> {
            byte[] ib = new byte[inputStream.available()];
            inputStream.read(ib);
            output.write(ib);
        };
    }
}
