package org.konghao.student.ws.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.konghao.student.web.LoginUserContext;

public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	public void close(MessageContext ctx) {
		
	}

	public boolean handleFault(SOAPMessageContext ctx) {
		return false;
	}

	public boolean handleMessage(SOAPMessageContext ctx) {
		Boolean out = (Boolean)ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
		try {
			if(out) {
				SOAPEnvelope enve = ctx.getMessage().getSOAPPart().getEnvelope();
				SOAPBody body = enve.getBody();
				String name = body.getFirstChild().getLocalName();
				if(name.equals("getStudent")) {
					SOAPHeader header = enve.getHeader();
					if(header==null) header = enve.addHeader();
					QName qn = new QName("http://ws.student.konghao.org","licenseInfo","ns");
					//无法通过该方式获取Servlet的Request，ThreadLocal
//					HttpServletRequest req = (HttpServletRequest)ctx.get(SOAPMessageContext.SERVLET_REQUEST);
//					System.out.println(req);
					String username = LoginUserContext.getLoginUser();
					if(username==null) username = "";
					header.addHeaderElement(qn).setValue(username);
				}
			}
			return true;
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Set<QName> getHeaders() {
		return null;
	}

}
