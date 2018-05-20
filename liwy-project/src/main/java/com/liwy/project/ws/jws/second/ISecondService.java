package com.liwy.project.ws.jws.second;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.liwy.project.entity.User;

@WebService
public interface ISecondService {
	@WebMethod
	public String sayHello(@WebParam(name = "name") String name);

	@WebMethod
	public List<String> map2List(Map<String, String> map);

	@WebMethod
	public User upStudent(@WebParam(name = "student") User student);
}
