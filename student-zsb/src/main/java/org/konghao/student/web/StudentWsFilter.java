package org.konghao.student.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StudentWsFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest r = (HttpServletRequest)req;
			String username = (String)r.getSession().getAttribute("loginUser");
			if(username!=null) {
				LoginUserContext.setLoginUser(username);
			}
			chain.doFilter(req, resp);
		} finally {
			LoginUserContext.removeLoginUser();
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
