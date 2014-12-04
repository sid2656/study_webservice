package org.konghao.student.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		//获取spring的工厂
		WebApplicationContext wac = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
		WebContextUtil.setWac(wac);
		super.init();
	}
}
