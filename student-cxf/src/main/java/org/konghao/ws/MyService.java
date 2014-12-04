package org.konghao.ws;

import javax.jws.WebService;

@WebService(endpointInterface="org.konghao.ws.IMyService")
public class MyService implements IMyService {

	public String sayHello(String name) {
		System.out.println(name);
		return "hello:"+name;
	}

}
