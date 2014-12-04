package com.sid.test;


import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

import com.sid.webservice.IMyService;
import com.sid.webservice.MyServiceImplService;
import com.sid.webservice.User;

public class SoapClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:9898/ms?wsdl");
		QName qname = new QName("http://www.sid.com/webservice", "MyServiceImplService");
		MyServiceImplService mis = new MyServiceImplService(url,qname);
		IMyService ms = mis.getMyServiceImplPort();
		try {
//			ms.login("admin","admin");
			User u = new User();
			u.setId(1); u.setUsername("123"); u.setPassword("23"); u.setNickname("23");
			Holder<User> user = new Holder<User>(u);
			ms.addUser(user);
		} catch (SOAPFaultException e) {
			System.out.println(e.getMessage());
		}
	}
}
