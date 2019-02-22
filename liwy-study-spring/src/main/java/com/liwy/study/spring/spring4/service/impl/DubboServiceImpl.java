package com.liwy.study.spring.spring4.service.impl;

import com.liwy.study.spring.spring4.service.IDubboService;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/22 14:49 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Path("users")
public class DubboServiceImpl implements IDubboService {
    @POST
    @Path("sayHello/{name : \\w+}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public String sayHello(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
