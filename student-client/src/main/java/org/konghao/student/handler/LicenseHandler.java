package org.konghao.student.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	public void close(MessageContext ctx) {
		
	}

	public boolean handleFault(SOAPMessageContext ctx) {
		return false;
	}

	public boolean handleMessage(SOAPMessageContext ctx) {
		try {
			Boolean out = (Boolean)ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if(out) {
				SOAPEnvelope enve = ctx.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				if(header==null) header = enve.addHeader();
				QName qname = new QName("http://ws.student.konghao.org","licenseInfo","ns");
				header.addHeaderElement(qname).setValue("kkkkkkkk");
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
