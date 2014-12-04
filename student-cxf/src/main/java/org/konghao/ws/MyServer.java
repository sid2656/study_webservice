package org.konghao.ws;


import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.konghao.ws.interceptor.LicenseUserInInterceptor;

public class MyServer {
	public static void main(String[] args) {
//		Endpoint.publish("http://localhost:8878/ms",new MyService());
		//CXF提供了另外一种发布方式
		JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
		fac.setAddress("http://localhost:8878/ms");
		fac.setServiceBean(new MyService());
		fac.setServiceClass(IMyService.class);
		//可以通过拦截器处理更多的SOAP请求
		fac.getInInterceptors().add(new LoggingInInterceptor());
//		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		//可以直接加入SOAPHandler
//		List<Handler> hans = new ArrayList<Handler>();
//		hans.add(new LiceseHander());
//		fac.setHandlers(hans);
		fac.getInInterceptors().add(new LicenseUserInInterceptor());
		fac.create();
	}
}
