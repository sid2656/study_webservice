package org.konghao.student.web;

import org.springframework.web.context.WebApplicationContext;

public class WebContextUtil {
	private static WebApplicationContext wac;
	private WebContextUtil(){}
	
	public static void setWac(WebApplicationContext wac) {
		WebContextUtil.wac = wac;
	}
	
	public static WebApplicationContext getWac() {
		return wac;
	}
	
	public static Object getBean(String name) {
		return wac.getBean(name);
	}
}
