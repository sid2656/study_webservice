package org.sid.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
//@SOAPBinding(style=Style.RPC)//默认是document，rpc不常用（rpc不进行元素的封装，直接在方法里面写参数，不能对参数加约定）
public interface IMyService {
	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);

	@WebResult(name="minusResult")
	public int minus(@WebParam(name="a")int a,@WebParam(name="b")int b);

	@WebResult(name="login")
	public User login(@WebParam(name="username")String username,@WebParam(name="password")String password);
	
}
