package com.liwy.project.ws.cxf.restful;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class RestServer {
	public static void main(String[] args) {

		// JAXRSServerFactoryBean发布REST的服务
		JAXRSServerFactoryBean jaxRSServerFactoryBean = new JAXRSServerFactoryBean();
		// 设置服务地址
		jaxRSServerFactoryBean.setAddress("http://localhost:80/service");
		// 设置服务实现类
		jaxRSServerFactoryBean.setServiceBean(new RestFulServiceImpl());
		// 设置资源类，如果有多个资源类，可以以“,”隔开。
		jaxRSServerFactoryBean.setResourceClasses(RestFulServiceImpl.class);

		// 支持json的处理器
		jaxRSServerFactoryBean.setProvider(new JacksonJsonProvider());

		// 发布服务
		jaxRSServerFactoryBean.create();
		System.out.println("rest服务发布了！");
	}
}
