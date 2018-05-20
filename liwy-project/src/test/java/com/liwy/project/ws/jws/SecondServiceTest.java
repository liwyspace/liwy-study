package com.liwy.project.ws.jws;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liwy.project.ws.jws.secondserver.ISecondService;
import com.liwy.project.ws.jws.secondserver.Map2List;
import com.liwy.project.ws.jws.secondserver.SecondServiceImplService;
import com.liwy.project.ws.jws.secondserver.User;

/**
 * 
 * @author liwy
 * 
 */
public class SecondServiceTest {
	private static final transient Logger log = LoggerFactory
			.getLogger(SecondServiceTest.class);

	/**
	 * <p>
	 * 通过wsimport生成相关类
	 * <p>
	 * wsimport -s . -p com.liwy.project.ws.jws.secondserver
	 * http://localhost/Webservices/secondService?wsdl
	 */
	@Test
	public void testSecondService() {
		SecondServiceImplService secondServiceService = new SecondServiceImplService();
		ISecondService firstService = secondServiceService
				.getSecondServiceImplPort();

		// 调用sayHello
		log.info(firstService.sayHello("123456"));

		// 调用map2List
		Map2List.Arg0 arg = new Map2List.Arg0();
		Map2List.Arg0.Entry entry = new Map2List.Arg0.Entry();
		entry.setKey("xf");
		entry.setValue("nanan");
		arg.getEntry().add(entry);
		Map2List.Arg0.Entry entry1 = new Map2List.Arg0.Entry();
		entry1.setKey("lg");
		entry1.setValue("yao");
		arg.getEntry().add(entry1);

		List<String> list = firstService.map2List(arg);
		for (String str : list) {
			log.info(str);
		}

		// 调用upStudent
		User student = new User();
		student.setUsername("student");
		student.setPassword("666");
		User stu = firstService.upStudent(student);
		System.out.println("name:" + stu.getUsername() + " pwd:"
				+ stu.getPassword());

	}
}
