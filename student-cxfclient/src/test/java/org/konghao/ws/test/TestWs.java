package org.konghao.ws.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.konghao.ws.IMyService;
import org.konghao.ws.MyServiceService;
import org.konghao.ws.handler.LicenseHandler;
import org.konghao.ws.interceptor.LicenseUserOutInterceptor;

import cal.cxf.konghao.org.CalWsService;
import cal.cxf.konghao.org.ICalWsService;

public class TestWs {

	@Test
	public void test01() {
		//基于JAX-WS的方式
		IMyService ms = new MyServiceService().getMyServicePort();
//		System.out.println(ms.sayHello("张三"));
	}
	
	@Test
	public void test02() {
		//使用CXF的方式访问
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setServiceClass(IMyService.class);
		fac.setAddress("http://localhost:8878/ms");
		//可以通过CXF为访问增加相应的Interceptor来处理进去和出来的消息
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		//可以非常简单的处理SOAPHandler
		List<Handler> hans = new ArrayList<Handler>();
		hans.add(new LicenseHandler());
		fac.setHandlers(hans);
		IMyService ms = (IMyService)fac.create();
		System.out.println(ms.sayHello("张三"));
//		SayHello sh = new SayHello();
//		sh.setName("张三");
//		System.out.println(ms.sayHello(sh,"admin"));
	}
	
	@Test
	public void test03() {
		//使用CXF的方式访问
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setServiceClass(IMyService.class);
		fac.setAddress("http://localhost:8878/ms");
		//可以通过CXF为访问增加相应的Interceptor来处理进去和出来的消息
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
//		fac.getOutInterceptors().add(new LicenseOutInterceptor());
		fac.getOutInterceptors().add(new LicenseUserOutInterceptor());
		IMyService ms = (IMyService)fac.create();
		System.out.println(ms.sayHello("张三"));
//		SayHello sh = new SayHello();
//		sh.setName("张三");
//		System.out.println(ms.sayHello(sh,"admin"));
	}
	
	@Test
	public void test04() {
		ICalWsService cws = new CalWsService().getCalWsPort();
		System.out.println(cws.add(12, 33));
	}
	
	@Test
	public void test05() {
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setServiceClass(ICalWsService.class);
		fac.setAddress("http://localhost:8889/ms");
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		ICalWsService cws = (ICalWsService)fac.create();
		System.out.println(cws.add(22, 12));
	}
}
