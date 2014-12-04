package org.sid.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;


@WebService(targetNamespace="http://www.sid.com/webservice",endpointInterface="org.sid.service.IMyService")
@HandlerChain(file="handler_chain.xml")
public class MyServiceImpl implements IMyService {
	private static List<User> users =  new ArrayList<User>();
	
	public MyServiceImpl(){
		users.add(new User(1, "admin", "管理员", "admin"));
	}

	@Override
	public int add(int a, int b) {
		System.out.println("a+b="+(a+b));
		return a+b;
	}

	@Override
	public User addUser(User user) {
		users.add(user);
		return user;
	}

	@Override
	public User login(String username, String password) throws UserException{
		for (User user : users) {
			if (username.equals(user.getUsername())&&password.equals(user.getPassword())) {
				return user;
			}
		}
		throw new UserException("用户不存在");
	}

	@Override
	public List<User> list(String authInfo) {
		System.out.println(authInfo);
		return users;
	}

}
