package org.sid.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.sid.service.User;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SAAJ
 * 没有之前的client是因为；之前的是将soap包装了，而这个方法是更底层的soap
 * Soap attachment api for java
 *
 */
public class TestSoap {
	String ns = "http://www.sid.com/webservice";
	String wsdlUrl = "http://localhost:8989/ms?wsdl";

	/**
	 * soap消息的分析和创建
	 */
	@Test
	public void test01(){
		try {
			//1.创建消息工厂
			MessageFactory factory = MessageFactory.newInstance();
			//2.根据消息工厂创建soapMessage
			SOAPMessage message = factory.createMessage();
			//3.创建SoapPart
			SOAPPart part = message.getSOAPPart();
			//4.获取SOAPEnvelope
			SOAPEnvelope envelope = part.getEnvelope();
			//5.可以通过soapEnvelope有效的获取相应的body和header等信息
			SOAPBody body = envelope.getBody();
			//6.根据Qname创建相应的节点（Qname就是一个带有命名空间的节点）
			QName qname = new QName(ns, "add", "ns");
			body.addBodyElement(qname).setValue("123123");
			//如果用一下方式进行设置会将<>转换为&lt;和&gt
//			body.addBodyElement(qname).setValue("<a>2</a><a>2</a>");
			SOAPBodyElement ele = body.addBodyElement(qname);
			ele.addChildElement("a").setValue("11");
			ele.addChildElement("b").setValue("22");
			//打印消息
			message.writeTo(System.out);
			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * soap消息的传递和处理
	 * 加入了schema的命名空间问题
	 */
	@Test
	public void test02(){
		try {
			//1.创建服务（Service）
			URL url = new URL(wsdlUrl);
			QName qname = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qname);
			
			//2.创建Dispatch
			//Service.Mode.MESSAGE会将<>转换了
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), 
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3.创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			
			//4.创建Qname来制定消息中传递的数据
			QName ename = new QName(ns,"add","nn");//<nn:add xmlns="xx">
			SOAPBodyElement ele = body.addBodyElement(ename);
			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("33");
			msg.writeTo(System.out);
			
			System.out.println("\n invoking……");
			//5.通过Dispatch传递消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			
			//将响应的消息转换为dom对象
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			String str = doc.getElementsByTagName("add").item(0).getTextContent();
			System.out.println("\n"+str);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * payload方式
	 * add用户
	 */
	@Test
	public void test03(){
		try {
			//1.创建服务（Service）
			URL url = new URL(wsdlUrl);
			QName qname = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qname);
			
			//2.创建Dispatch，通过Source方式传递
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), 
					Source.class, Service.Mode.PAYLOAD);
			
			//3.根据用户对象创建相应的xml
			User user = new User(3,"zhangsan","张三","zhangsan");
			JAXBContext ctx = JAXBContext.newInstance(User.class);
			Marshaller mar = ctx.createMarshaller();
			StringWriter writer = new StringWriter();
			//去掉startDocument和endDocument
			mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
			mar.marshal(user, writer);
			
			//4.封装相应的part addUser
			String payload = "<nn:addUser xmlns:nn=\""+ns+"\">"+writer.toString()+"</nn:addUser>";
			System.out.println(payload);
			StreamSource rs = new StreamSource(new StringReader(payload));
			
			//5.通过dispatch传递payload
			Source response = dispatch.invoke(rs);
			
			//6.将Source转换为DOM进行操作，使用Transform对象转换
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			DOMResult result = new DOMResult();
			tran.transform(response, result);

			//7.处理响应信息
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.evaluate("//user", result.getNode(),XPathConstants.NODESET);
			System.out.println(nl.item(0).getNodeName());
			//反编排
			User ru = (User) ctx.createUnmarshaller().unmarshal(nl.item(0));
			System.out.println(ru.getNickname());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}


	/**
	 * message方式处理list
	 */
	@Test
	public void test04(){
		try {
			//1.创建服务（Service）
			URL url = new URL(wsdlUrl);
			QName qname = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qname);
			
			//2.创建Dispatch
			//Service.Mode.MESSAGE会将<>转换了
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), 
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3.创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			//处理header信息
			SOAPHeader header = envelope.getHeader();
			if (header==null) {
				header = envelope.addHeader();
			}
			QName hname = new QName(ns,"authInfo","nn");
			header.addHeaderElement(hname).setValue("USER");
			
			//4.创建Qname来制定消息中传递的数据
			QName ename = new QName(ns,"list","nn");//<nn:add xmlns="xx">
			SOAPBodyElement ele = body.addBodyElement(ename);
			msg.writeTo(System.out);
			System.out.println("\n invoking……");
			
			//5.通过Dispatch传递消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			
			//将响应的消息转换为dom对象
			Document doc = response.getSOAPBody().extractContentAsDocument();
			NodeList nl = doc.getElementsByTagName("user");
			JAXBContext ctx = JAXBContext.newInstance(User.class);
			for (int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				User u = (User) ctx.createUnmarshaller().unmarshal(n);
				System.out.println(u.getNickname());
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}


	/**
	 * message方式处理login
	 * 加入异常处理方式
	 * 异常这样加入和定义：
	 * <fault message="tns:UserException" name="UserException" wsam:Action="http://www.sid.com/webservice/IMyService/login/Fault/UserException"/>
	 * fault标签要位于output标签之后
	 * 
	 */
	@Test
	public void test05(){
		try {
			//1.创建服务（Service）
			URL url = new URL(wsdlUrl);
			QName qname = new QName(ns, "MyServiceImplService");
			Service service = Service.create(url, qname);
			
			//2.创建Dispatch
			//Service.Mode.MESSAGE会将<>转换了
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServiceImplPort"), 
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3.创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			
			//4.创建Qname来制定消息中传递的数据
			QName ename = new QName(ns,"login","nn");//<nn:add xmlns="xx">
			SOAPBodyElement ele = body.addBodyElement(ename);
			ele.addChildElement("username").setValue("ss");
			ele.addChildElement("password").setValue("ss");
			msg.writeTo(System.out);
			System.out.println("\n invoking……");
			
			//5.通过Dispatch传递消息
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SOAPFaultException e) {
			System.out.println(e.getMessage());;
		}
	}
}
