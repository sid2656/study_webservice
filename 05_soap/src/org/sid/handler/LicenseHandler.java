package org.sid.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {
	String ns = "http://www.sid.com/webservice";

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("=========Server handleMessage=========");
		try {
			//往服务器发信息
			Boolean out = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (!out) {
				SOAPMessage message = context.getMessage();
				//1.判断message中是否存在header
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				SOAPBody body = envelope.getBody();
				Node bn = body.getChildNodes().item(0);
				//获取part的name
				String partName = bn.getLocalName();
				if ("list".equals(partName)||"addUser".equals(partName)) {
					System.out.println("list or addUser");
					Iterator<SOAPHeaderElement> iterator = header.examineAllHeaderElements();
					if (header==null||!iterator.hasNext()) {
						//添加一个错误信息
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空");
						throw new SOAPFaultException(fault);
					}else{
						while (iterator.hasNext()) {
							SOAPHeaderElement ele = iterator.next();
							System.out.println(ele.getTextContent());
						}
					}
					message.writeTo(System.out);
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
