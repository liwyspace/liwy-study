package com.liwy.project.ws.jws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.liwy.project.entity.User;

@WebService
public class FirstService {

	public String sayHello(@WebParam(name = "me-name") String name) {
		return "Hello " + name;
	}

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

	public User upStudent(User student) {
		student.setUsername("liwy");
		student.setPassword("123456");
		return student;
	}
}
