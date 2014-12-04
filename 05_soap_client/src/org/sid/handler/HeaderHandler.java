package org.sid.handler;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
	String ns = "http://www.sid.com/webservice";

	@Override
	public void close(MessageContext arg0) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	/**
	 * 
	 * LogicalHandler只能获得SOAPBody信息
	 * SOAPHandler可以获取SOAPMessage信息
	 * 客户端先处理LogicalHandler
	 * 服务器端先处理SOAPHandler
	 * 1.创建一个类实现SOAPHandler（并加入<SOAPMessageContext>）
	 * 2.在handlerMessage中编写代码
	 * 3.配置Handler
	 * 4.在服务上开启handler(在客户端MyServiceImplService中加上@HandlerChain(file="handler_chain.xml"))
	 * 
	 * 服务器获取
	 * 1.编写handler
	 * 2.在服务器端显示声明一个变量（基于契约优先）
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("=========handleMessage=========");
		try {
			//往服务器发信息
			Boolean out = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (out) {
				SOAPMessage message = context.getMessage();
				//1.判断message中是否存在header
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				//2.获取body，判断是partName
				SOAPBody body = envelope.getBody();
				Node node = body.getChildNodes().item(0);
				String partname = node.getLocalName();
				if ("list".equals(partname)||"addUser".equals(partname)) {
					System.out.println("list or addUser");
					SOAPHeader header = envelope.getHeader();
					if (header==null) {
						header = envelope.addHeader();
					}
					QName qname = new QName(ns, "licenseInfo","nn");
					header.addHeaderElement(qname).setValue("123123");
					message.writeTo(System.out);
					System.out.println();
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
