package org.example.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;


public class Test {

	public static void main(String[] args) {
		String ns = "http://www.example.org/mywsdl/";
		try {
			QName name = new QName(ns,"MyServiceImplService");
			URL url = new URL("http://localhost:8989/ms?wsdl");
//			MyServiceImplService mis = new MyServiceImplService(url,name);
//			IMyService ms = mis.getMyServiceImplPort();
//			System.out.println(ms.divide(19, 2));
			//使用soap来传递消息
			Service service = Service.create(url,name);
			QName pname = new QName(ns,"MyServiceImplPort");
			Dispatch<SOAPMessage> dispatch  = service.createDispatch(pname, SOAPMessage.class, Service.Mode.MESSAGE);
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope enve = msg.getSOAPPart().getEnvelope();
			SOAPHeader header = enve.getHeader();
			SOAPBody body = enve.getBody();
			if (header==null) {
				header = enve.addHeader();
			}
			QName hname = new QName(ns,"licenseInfo","ns");
			header.addHeaderElement(hname).setValue("sssssssss");
			QName bname = new QName(ns,"add","ns");
			SOAPBodyElement ele = body.addBodyElement(bname);
			ele.addChildElement("a").setValue("12");
			ele.addChildElement("b").setValue("12");
			
			msg.writeTo(System.out);
			System.out.println("\n invoking。。。。。。。。");
			
			SOAPMessage rep = dispatch.invoke(msg);
			rep.writeTo(System.out);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
