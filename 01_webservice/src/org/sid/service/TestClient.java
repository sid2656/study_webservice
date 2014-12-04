package org.sid.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestClient {
	public static void main(String[] args) {
		try {
			//创建访问的wsdl服务器地址url
			URL url = new URL("http://localhost:6666/ns?wsdl");
			//通过Qname指明服务的具体信息namespaceURI和localPart在http://localhost:6666/ns?wsdl中查找
			QName qname = new QName("http://service.sid.org/","MyServiceImplService");
			//创建服务
			Service service = Service.create(url, qname);
			//实现接口（依赖于接口了）
			IMyService ms = service.getPort(IMyService.class);
			ms.add(23, 21);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
