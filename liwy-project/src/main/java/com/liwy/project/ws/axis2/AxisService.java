package com.liwy.project.ws.axis2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liwy.project.entity.User;

public class AxisService {
	public String sayHello(String name) {
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
		student.setId(2L);
		student.setRoleIdsStr("1,2,3");
		student.setLocked(true);
		student.setOrganizationId(22L);
		student.setSalt("111");
		return student;
	}
}
