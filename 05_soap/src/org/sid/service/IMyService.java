package org.sid.service;


import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(targetNamespace="http://www.sid.com/webservice")
public interface IMyService {
	@WebResult(name="add")
	public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);

	@WebResult(name="user")
	public User addUser(@WebParam(name="user")User user);

	@WebResult(name="user")
	public User login(@WebParam(name="username")String username,
			@WebParam(name="password")String password) throws UserException;

	/**
	 * 设置验证信息
	 * 并且验证信息已头的方式来进行传递，不通过body来传递
	 * @param authInfo
	 * @return
	 */
	@WebResult(name="user")
	public List<User> list(@WebParam(header=true,name="authInfo")String authInfo);
}
