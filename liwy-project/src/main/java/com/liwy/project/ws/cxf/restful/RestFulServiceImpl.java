package com.liwy.project.ws.cxf.restful;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liwy.project.entity.User;

public class RestFulServiceImpl implements IRestFulService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public List<String> map2List(Map<String, String> map) {
		List<String> list = new ArrayList<String>();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			list.add(key + ":" + value);
		}
		return list;
	}

	@Override
	public User upStudent(User student) {
		student.setUsername("liwy");
		student.setPassword("123456");
		return student;
	}

	@Override
	public User getUser(long id) {
		User user = new User();
		user.setId(id);
		user.setUsername("liwy");
		user.setPassword("123456");
		return user;
	}

	@Override
	public User getUser2(long id) {
		User user = new User();
		user.setId(id);
		user.setUsername("liwy");
		user.setPassword("123456");
		return user;
	}

	@Override
	public String insertUser(User student) {
		System.out.println("####################################");
		System.out.println(student.toString());
		return "插入成功：" + student.getUsername();
	}
}
