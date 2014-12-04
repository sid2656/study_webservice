package org.zttc.service.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import cn.edu.zttc.service.IUserService;
import cn.edu.zttc.service.LicenceInfo;
import cn.edu.zttc.service.User;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;



public class WebUtil {
	private static String ns = "http://service.zttc.edu.cn";
	public static void addLicenceHeader(IUserService port,HttpServletRequest request) {
		try {
			//1、将一个对象转换为xml通过JAXB
			JAXBContext ctx = JAXBContext.newInstance(LicenceInfo.class);
			User ru = (User)request.getSession().getAttribute("loginUser");
			if(ru==null) return;
			LicenceInfo info = new LicenceInfo();
			info.setRegistUser(ru);
			QName name = new QName(ns,"licenceInfo");
			JAXBElement<LicenceInfo> jele = new JAXBElement<LicenceInfo>(name,LicenceInfo.class,info); 
			Marshaller mars = ctx.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT,true);
			mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			//2、转换为DOM
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			mars.marshal(jele, doc);
			
			//3、通过Headers.create方法完成header的添加
			//获取WSBindingProvider
			WSBindingProvider wsb = (WSBindingProvider)port;
			wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
