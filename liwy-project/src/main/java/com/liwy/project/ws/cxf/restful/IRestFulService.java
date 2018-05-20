package com.liwy.project.ws.cxf.restful;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.liwy.project.entity.User;

@Path("/hello")
public interface IRestFulService {
	@GET
	@Path("/sayHello/{name}")
	public String sayHello(@PathParam("name") String name);

	@POST
	@Path("/map2list")
	@Consumes(MediaType.APPLICATION_JSON) // 接收客户端的格式
	@Produces(MediaType.APPLICATION_JSON) // 返回客户端格式
	public List<String> map2List(Map<String, String> map);

	@GET
	@Path("/getUser/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("id") long id);

	@GET
	@Path("/getUser2/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser2(@PathParam("id") long id);

	@POST
	@Path("/insertUser")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 接收客户端的格式
	public String insertUser(User student);

	@POST
	@Path("/upStudent")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 接收客户端的格式
	@Produces(MediaType.APPLICATION_JSON) // 返回客户端格式
	public User upStudent(User student);
}
