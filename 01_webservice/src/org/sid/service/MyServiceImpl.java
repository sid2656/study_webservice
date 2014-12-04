package org.sid.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface="org.sid.service.IMyService")
public class MyServiceImpl implements IMyService {

	@Override
	public int add(int a, int b) {
		System.out.println(a+b);
		return a+b;
	}

	@Override
	public int minus(int a, int b) {
		System.out.println(a-b);
		return a-b;
	}

	@Override
	@WebResult(name = "login")
	public User login(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

}
