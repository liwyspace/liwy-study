package com.liwy.project.ws.axis;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.Test;

import com.liwy.project.ws.axis.stubs.AxisServiceStub;

public class AxisTest {
	@Test
	public void testAxisPojo() throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();

		EndpointReference targetEPR = new EndpointReference(
				"http://localhost:8080/axis2/services/FirstAxisService");
		options.setTo(targetEPR);

		// 参数
		Object[] entryArgs = new Object[] { "liwy" };
		// 返回值
		Class[] classes = new Class[] { String.class };

		// 命名空间：与wsdl文件中的wsdl:definitions标签targetNamespace属性
		QName opName = new QName("http://ws.apache.org/axis2", "sayHello");

		// 有返回值
		Object result = serviceClient
				.invokeBlocking(opName, entryArgs, classes)[0];
		System.out.println(result);

		opName = new QName("http://ws.apache.org/axis2", "send");
		// 无返回值
		serviceClient.invokeRobust(opName, new Object[] { 88 });
	}

	@Test
	public void testAxisAXIOM() throws AxisFault {
		EndpointReference targetEPR = new EndpointReference(
				"http://localhost:8080/axis2/services/FirstAxisService");
		Options options = new Options();
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://ws.apache.org/axis2",
				"axis2");

		OMElement method = fac.createOMElement("sayHello", omNs);
		OMElement value = fac.createOMElement("name", omNs);
		value.addChild(fac.createOMText(value, "liwy222"));
		method.addChild(value);
		OMElement result = sender.sendReceive(method);
		System.out.println(result);
	}

	@Test
	public void testAxisRest() throws AxisFault {
		EndpointReference targetEPR = new EndpointReference(
				"http://localhost:8080/axis2/services/FirstAxisService");
		Options options = new Options();
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		// 客户端REST方式调用服务跟普通服务的区别，REST调用必须加上下面这个代码。
		options.setProperty(Constants.Configuration.ENABLE_REST,
				Constants.VALUE_TRUE);

		ServiceClient sender = new ServiceClient();
		// sender.engageModule(Constants.MODULE_ADDRESSING);
		sender.setOptions(options);

		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://ws.apache.org/axis2",
				"axis2");

		OMElement method = fac.createOMElement("sayHello", omNs);
		OMElement value = fac.createOMElement("name", omNs);
		value.addChild(fac.createOMText(value, "liwy222"));
		method.addChild(value);
		OMElement result = sender.sendReceive(method);
		System.out.println(result);

		try {
			XMLStreamWriter writer = XMLOutputFactory.newInstance()
					.createXMLStreamWriter(System.out);
			result.serialize(writer);
			writer.flush();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAPI() throws RemoteException {
		AxisServiceStub axisServiceStub = new AxisServiceStub();
		AxisServiceStub.SayHello sayHello = new AxisServiceStub.SayHello();
		sayHello.setName("liwy");
		String result = axisServiceStub.sayHello(sayHello).get_return();
		System.out.println(result);

		AxisServiceStub.Map2List map2List = new AxisServiceStub.Map2List();
		AxisServiceStub.Map1 map1 = new AxisServiceStub.Map1();
		AxisServiceStub.Entry1 entry1 = new AxisServiceStub.Entry1();
		entry1.setKey("1");
		entry1.setValue("liwy");
		map1.addEntry(entry1);
		AxisServiceStub.Entry1 entry2 = new AxisServiceStub.Entry1();
		entry2.setKey("2");
		entry2.setValue("liwy222");
		map1.addEntry(entry2);
		map2List.setMap(map1);
		String[] result2 = axisServiceStub.map2List(map2List).get_return();
		for (String s : result2) {
			System.out.println(s);
		}

		AxisServiceStub.UpStudent upStudent = new AxisServiceStub.UpStudent();
		AxisServiceStub.User user = new AxisServiceStub.User();
		user.setId(1L);
		user.setUsername("nnnn");
		user.setPassword("9999");
		user.setLocked(false);
		user.setCredentialsSalt("111");
		user.setRoleIdsStr("1,2,3");
		user.setRoleIds(new long[] { 1L, 2L, 3L });
		upStudent.setStudent(user);
		AxisServiceStub.User user2 = axisServiceStub.upStudent(upStudent)
				.get_return();
		System.out.println(user2.getUsername() + ":" + user2.getPassword());

	}
}
