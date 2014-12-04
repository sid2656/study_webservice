package org.zttc.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import cn.edu.zttc.service.IUserService;
import cn.edu.zttc.service.User;
import cn.edu.zttc.service.UserRunTimeException_Exception;
import cn.edu.zttc.service.UserService;

public class TestService {

	private IUserService port;
	private UserService us;
	private String ns = "http://service.zttc.edu.cn";
	
	@Before
	public void init(){
		try {
			URL url = new URL("http://localhost:8888/us?wsdl");
			QName name = new QName(ns,"UserService");
			us = new UserService(url,name);
			port = us.getUserServicePort();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testList(){
		List<User> list = port.list();
		
		for (User user : list) {
			System.out.println(user.getNickname());
		}
	}

	@Test
	public void testAdd(){
		try{
			User user = new User();
			user.setNickname("搜索");
			user.setPassword("456123");
			user.setUsername("ss");
			port.add(user);
		}catch (UserRunTimeException_Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testLogin(){
		try{
			User u = port.login("ss", "456123");
			System.out.println(u.getNickname());
		}catch (UserRunTimeException_Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testDelete(){
		port.delete("ss");
	}
}
