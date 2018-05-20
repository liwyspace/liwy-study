package com.liwy.project.ws.jws.second;

import javax.xml.ws.Endpoint;

public class Server {
	public static void main(String[] args) {
		System.out.println("启动服务");
		Endpoint.publish("http://localhost:80/Webservices/secondService",
				new SecondServiceImpl());
	}
}
