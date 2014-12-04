package org.zttc.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.MTOMFeature;

import org.apache.commons.io.FileUtils;
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
			URL url = new URL("http://localhost:8989/web-soa/us?wsdl");
			QName name = new QName(ns,"UserService");
			us = new UserService(url,name);
			port = us.getUserServicePort(new MTOMFeature());
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
		try {
			port.delete("ss");
		} catch (UserRunTimeException_Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpload() {
		try {
			byte[] file = FileUtils.readFileToByteArray(new File("E:/sid/original_HCIQ_5d5300000148125b.jpg"));
			port.upload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
