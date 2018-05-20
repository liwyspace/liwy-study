package com.liwy.project.ws.cxf;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liwy.project.ws.cxf.cxfserver.CxfServiceImplService;
import com.liwy.project.ws.cxf.cxfserver.ICxfService;
import com.liwy.project.ws.cxf.cxfserver.Map2List;
import com.liwy.project.ws.cxf.cxfserver.User;

public class CxfServiceTest {
	private static final transient Logger log = LoggerFactory
			.getLogger(CxfServiceTest.class);

	/**
	 * <p>
	 * 通过cxf提供的wsdl2java工具生成客户端代码
	 * <p>
	 * wsdl2java -d ./src/test/java -p com.liwy.project.ws.cxf.cxfserver
	 * http://localhost/Webservices/cxfService?wsdl
	 */
	@Test
	public void testCxfService() {
		CxfServiceImplService service = new CxfServiceImplService();
		ICxfService cxfService = service.getCxfServiceImplPort();

		// 调用sayHello
		log.info(cxfService.sayHello("123456"));

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

		List<String> list = cxfService.map2List(arg);
		for (String str : list) {
			log.info(str);
		}

		// 调用upStudent
		User student = new User();
		student.setUsername("student");
		student.setPassword("666");
		User stu = cxfService.upStudent(student);
		System.out.println("name:" + stu.getUsername() + " pwd:"
				+ stu.getPassword());
	}
}
