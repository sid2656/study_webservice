package org.example.mywsdl;

import javax.xml.ws.Endpoint;

public class MyServer {

	public static void main(String[] args) {
		//地址是在wsdl中编写的
		Endpoint.publish("http://localhost:8989/ms", new MyServiceImpl());
	}
}
