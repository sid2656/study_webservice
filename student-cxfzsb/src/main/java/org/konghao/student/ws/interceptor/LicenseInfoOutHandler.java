package org.konghao.student.ws.interceptor;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;
import org.konghao.student.web.LoginUserContext;
import org.springframework.stereotype.Service;

@Service("licenseInfoOutHandler")
public class LicenseInfoOutHandler extends AbstractSoapInterceptor {

	public LicenseInfoOutHandler() {
		super(Phase.WRITE);
	}
	
	public void handleMessage(SoapMessage message) throws Fault {
		try {
			List<Header> hs = message.getHeaders();
			QName qn = new QName("http://ws.student.konghao.org", "licenseInfo","ns");
			DataBinding db = new JAXBDataBinding(String.class);
			Header h = new Header(qn,LoginUserContext.getLoginUser(),db);
			hs.add(h);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
