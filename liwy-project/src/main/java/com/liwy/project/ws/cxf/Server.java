package com.liwy.project.ws.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {
	public static void main(String[] args) {

		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(CxfServiceImpl.class);
//		factory.setServiceBean(new CxfServiceImpl());
		factory.setAddress("http://localhost:80/Webservices/cxfService");

		org.apache.cxf.endpoint.Server server = factory.create();
		server.start();
	}
}
