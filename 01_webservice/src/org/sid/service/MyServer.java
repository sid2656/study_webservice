package org.sid.service;

import javax.xml.ws.Endpoint;

public class MyServer {
	public static void main(String[] args) {
		String address = "http://localhost:6666/ns";
		Endpoint.publish(address, new MyServiceImpl());
	}
}