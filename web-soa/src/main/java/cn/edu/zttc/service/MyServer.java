package cn.edu.zttc.service;

import javax.xml.ws.Endpoint;

public class MyServer {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9898/us", new UserServiceImpl());
	}
}
