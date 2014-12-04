package org.zttc.service.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import cn.edu.zttc.service.IUserService;
import cn.edu.zttc.service.User;
import cn.edu.zttc.service.UserRunTimeException_Exception;
import cn.edu.zttc.service.UserService;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService port;
	private UserService userService;
	private String ns = "http://service.zttc.edu.cn";
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method= request.getParameter("method");
		URL url = new URL("http://localhost:8989/web-soa/us?wsdl");
		QName name = new QName(ns,"UserService");
		userService = new UserService(url,name);
		port = userService.getUserServicePort();
		if (null==method||"".equals(method)) {
			list(request, response);
		}else if ("add".equals(method)) {
			add(request, response);
		}else if ("delete".equals(method)) {
			delete(request, response);
		}else if ("login".equals(method)) {
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User u = port.login(username, password);
			request.getSession().setAttribute("loginUser", u);
			response.sendRedirect("user.do");
		} catch (UserRunTimeException_Exception e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		WebUtil.addLicenceHeader(port, request);
		String username = request.getParameter("username");
		try {
			port.delete(username);
			response.sendRedirect("user.do");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserRunTimeException_Exception e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		WebUtil.addLicenceHeader(port, request);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		User u = new User();
		u.setNickname(nickname);
		u.setPassword(password);
		u.setUsername(username);
		try {
			port.add(u);
			response.sendRedirect("user.do");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserRunTimeException_Exception e) {
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> list = port.list();
			request.setAttribute("users", list);
			RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
			dis.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
