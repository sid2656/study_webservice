package org.sid.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestClient {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:6666/ns?wsdl");
			//namespaceURI和localPart在http://localhost:6666/ns?wsdl中查找
			QName qname = new QName("http://service.sid.org/","MyServiceImplService");
			Service service = Service.create(url, qname);
			//依赖于接口了
			IMyService ms = service.getPort(IMyService.class);
			System.out.println(ms.add(23, 21));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
