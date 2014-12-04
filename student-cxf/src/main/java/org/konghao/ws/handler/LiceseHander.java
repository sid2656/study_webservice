package org.konghao.ws.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LiceseHander implements SOAPHandler<SOAPMessageContext> {

	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean handleFault(SOAPMessageContext arg0) {
		return false;
	}

	public boolean handleMessage(SOAPMessageContext ctx) {
		try {
			Boolean out = (Boolean) ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if(!out) {
				SOAPEnvelope enve = ctx.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				if(header==null) return true;
				@SuppressWarnings("unchecked")
				Iterator<SOAPHeaderElement> it = header.getChildElements();
				while(it.hasNext()) {
					SOAPHeaderElement ele = it.next();
					if(ele.getLocalName().equals("licenseInfo")) {
						System.out.println("Server:"+ele.getTextContent());
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Set<QName> getHeaders() {
		return null;
	}

}
