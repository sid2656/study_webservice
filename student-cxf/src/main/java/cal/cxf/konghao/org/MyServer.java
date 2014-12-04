package cal.cxf.konghao.org;


import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class MyServer {

	public static void main(String[] args) {
//		Endpoint.publish("http://localhost:8889/ms",new CalWsService());
		/**
		 * 通过以下方式发布的WebService并不支持契约优先
		 */
		JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
		fac.setAddress("http://localhost:8889/ms");
		fac.setServiceClass(ICalWsService.class);
		fac.setServiceBean(new CalWsService());
		//需要设置以下参数才能基于契约优先发布
		fac.setWsdlLocation("META-INF/wsdl/cal.wsdl");
		fac.setServiceName(new QName("http://org.konghao.cxf.cal/", "CalWsService"));
		fac.create();
	}

}
