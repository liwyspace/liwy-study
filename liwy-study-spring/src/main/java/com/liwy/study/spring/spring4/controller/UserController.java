package com.liwy.study.spring.spring4.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.liwy.study.spring.spring4.bean.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/19 14:06 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
//@RestController // rest风格接口，自动添加@ResponseBody注解
@RequestMapping("/user")
@SessionAttributes(names = {"student","user"}) // 同Model与Session中的数据
public class UserController {

    /**
     * 使用@PathVariable方法参数上的注释将其绑定到URI模板变量的值
     * 使用@RequestParam注释接收请求参数
     *
     * @param id
     * @return java.lang.String
     */
    @RequestMapping(path = "/group/{myGroup}/users/{id}", method = RequestMethod.GET, params = {"sex","type"})
    public String getUser(@PathVariable("myGroup") String type, @PathVariable Long id, @RequestParam String sex, Model model) {
        System.out.println(type);
        System.out.println(id);
        System.out.println(sex);
        return "user";
    }

    /**
     * 支持Ant样式(*)
     */
    @RequestMapping("/group/*/users/{id}/{day}")
    public void handle(@PathVariable String id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date day) {
        System.out.println(id);
        System.out.println(day);
    }

    /**
     * 通过正则配URI
     *
     * @param version
     * @param extension
     */
    @RequestMapping("/myapp/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void handle(@PathVariable String version,
                       @PathVariable String extension) {
        System.out.println(version);
        System.out.println(extension);
    }

    /**
     * 通过params请求参数匹配URI: myParam", "!myParam" 或 "myParam=myValue"
     * 通过headers请求头匹配URI
     *
     */
    @GetMapping(path = "/users", params = {"myParams","myParams1=myValue1"}, headers = "myHeader=myValue")
    public void findPet3() {
        System.out.println("成功匹配");
    }

    /**
     * 指定消费媒体类型： 只有当请求头中 Content-Type 的值与指定可消费的媒体类型中有相同的时候，请求才会被匹配
     * RequestBody注解 将请求主体绑定到方法参数
     *
     * @param user
     * @param model
     */
    @PostMapping(path = "/users", consumes = "application/json")
    public void addUser(@RequestBody User user, Model model) {
        System.out.println(user);
    }

    /**
     * 指定生产媒体类型： 只有当请求头中 Accept 的值与指定可生产的媒体类型中有相同的时候，请求才会被匹配。
     * ResponseBody将返回值映射到响应正文
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/usersjson/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User getUser(@PathVariable String id, Model model, HttpSession session, HttpServletResponse response) {
        User user = new User();
        user.setUsername("name111");
        user.setEmail("email@121.com");
        System.out.println(user);
        session.setAttribute("user", user);

        Cookie cookie = new Cookie("myCookie","liwycookie");
        cookie.setPath("/liwy-study-spring/user");
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        return user;
    }

    /**
     * 使用HttpEntity:
     * HttpEntity与@RequestBody和@ResponseBody很相似。除了能获得请求体和响应体中的内容之外，HttpEntity（
     * 以及专门负责处理响应的ResponseEntity子类）还可以存取请求头和响应头
     *
     * @param requestEntity
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/something")
    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity)
            throws UnsupportedEncodingException {
        String requestHeader = requestEntity.getHeaders().getFirst(
                "MyRequestHeader");
        byte[] requestBody = requestEntity.getBody();

        // do something with request header and body

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Hello World", responseHeaders,
                HttpStatus.CREATED);
    }

    /**
     * <pre>
     * 在方法上使用@ModelAttribute
     * 		@ModelAttribute 说明了方法的作用是用于添加一个或多个属性到model上
     * 		@ModelAttribute 的方法实际上会在@RequestMapping方法之前被调用
     *
     * 该方法的返回值被添加到model中名字为"account",你也可以通过@ModelAttribute("myAccount")来自定义名字
     *
     * <pre>
     *
     * @param number
     * @return
     */
//    @ModelAttribute
    public User addAccount(@RequestParam String number) {
        User user = new User();
        user.setUsername("在请求之前向model中添加属性");
        return user;
    }

    /**
     * 添加多个属性到model
     *
     * @param number
     * @param model
     */
//    @ModelAttribute
    public void populateModel(@RequestParam String number, Model model) {
        model.addAttribute("student","liwystu");
        User user = new User();
        user.setUsername("在请求之前向model中添加属性111");
        model.addAttribute("user1", user);
        // add more ...
    }

    /**
     * <pre>
     * 注解在方法参数上的@ModelAttribute说明了该方法参数的值将由model中取得。如果model中找不到，那么该参数会先被实例化，
     * 然后被添加到model中。
     *
     * 该参数实例可能来自以下几个地方:
     * 		它可能因为@SessionAttributes注解的使用已经存在于model中
     * 		它可能因为在同个控制器中使用了@ModelAttribute方法已经存在于model中
     * 		它可能是由URI模板变量和类型转换中取得的
     * 		它可能是调用了自身的默认构造器被实例化出来的
     * </pre>
     *
     * @param user
     * @return
     */
    @GetMapping("/getModelAttri")
    public void processSubmit(@ModelAttribute User user,@ModelAttribute("user1") User user1, @ModelAttribute("student") String student) {
        System.out.println(user);
        System.out.println(user1);
        System.out.println(student);
    }

    /**
     * 使用@SessionAttribute来访问预先存在的全局会话属性
     *
     * @param user
     * @return
     */
    @RequestMapping("/hd1")
    public void handle(@SessionAttribute User user, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
        System.out.println(user);
    }

    /**
     * 使用@RequestAttribute来访问请求属性
     *
     * @param parenet
     * @return
     */
    @RequestMapping("/hd2")
    public void handle2(@RequestAttribute String parenet) {
        System.out.println(parenet);
    }

    /**
     * <pre>
     * 使用@CookieValue注解映射Cookie值
     * 		JSESSIONID = 415A4AC178C59DACE0B2C9CA727CDD84
     * </pre>
     *
     * @param cookie
     */
    @RequestMapping("/cookie")
    public void displayHeaderInfo(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
    }

    /**
     * <pre>
     * 使用@RequestHeader注解映射请求头属性
     * 		Host                    localhost:8080
     * 		Accept                  text/html,application/xhtml+xml,application/xml;q=0.9
     * 		Accept-Language         fr,en-gb;q=0.7,en;q=0.3
     * 		Accept-Encoding         gzip,deflate
     * 		Accept-Charset          ISO-8859-1,utf-8;q=0.7,*;q=0.7
     * 		Keep-Alive              300
     * </pre>
     *
     * @param encoding
     * @param keepAlive
     */
    @RequestMapping("/displayHeaderInfo")
    public void displayHeaderInfo(
            @RequestHeader("Accept-Encoding") String encoding,
            @RequestHeader("Keep-Alive") long keepAlive) {
        System.out.println(encoding);
        System.out.println(keepAlive);
    }

    /**
     * 使用@InitBinder自定义数据绑定
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(
//                dateFormat, false));

        binder.addCustomFormatter(new DateFormatter("yyyyMMdd"));
    }

    @RequestMapping("/getDate/{date}")
    public void getDate(@PathVariable Date date) {
        System.out.println(date);
    }

    @RequestMapping("/{id}")
    @ResponseBody
    @JsonView(User.WithNameEmailView.class)
    public User getUserView(@PathVariable Long id) {
        User user = new User();
        user.setUsername("liwy");
        user.setEmail("liwey@126.com");
        user.setSex((byte) 1);
        user.setStatus((byte) 1);
        user.setRegisterTime(new Date());
        return user;
    }

    //测试全局自定义类型转换器
    @RequestMapping("/myConversion/{user}")
    public void testMyConversion(@PathVariable User user) {
        System.out.println(user);
    }

    // 测试自定义视图解析器
    @RequestMapping("/myView")
    public String myView() {
        return "myView";
    }
}
