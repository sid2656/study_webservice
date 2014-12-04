package org.xml.stax;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.EventFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 基于光标的
 * 都是java的api提供的
 */
public class TestStax {
	
	/**
	 * 输出节点类型的表示数字
	 * 基于光标的方式
	 */
	@Test
	public void test01(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					System.out.println(reader.getName());
				}else if (type == XMLStreamConstants.CHARACTERS) {
					System.out.println(reader.getText().trim());
				}else if (type == XMLStreamConstants.END_ELEMENT) {
					System.out.println(reader.getName());
				}
			}
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 输出存在的书的类型
	 * 基于光标的方式
	 */
	@Test
	public void test02(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName().toString();
					if (name.equals("book")) {
						System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));
					}
				}
			}
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 输出存在的书的类型
	 * 基于光标的方式
	 */
	@Test
	public void test03(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while (reader.hasNext()) {
				int type = reader.next();
				//判断节点类型是否是开始或者结束或者文本节点，之后根据情况进行处理
				//基于光标的方式
				if (type == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName().toString();
					if (name.equals("title")) {
						System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));
						System.out.print(reader.getElementText()+":");
					}
					if (name.equals("price")) {
						System.out.println(reader.getElementText()+":");
					}
				}
			}
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 输出存在的书的类型
	 * 基于迭代的方式
	 */
	@Test
	public void test04(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			//基于迭代模型的操作方式
			XMLEventReader reader = factory.createXMLEventReader(is);
			int num = 0;
			while (reader.hasNext()) {
				//通过XMLEvent来获取是否是某种节点
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					//通过event.asXxxxx来转换节点
					String name = event.asStartElement().getName().toString();
					if (name.equals("title")) {
						System.out.print(reader.getElementText()+":");
					}
					if (name.equals("price")) {
						System.out.println(reader.getElementText()+":");
					}
				}
				num++;
			}
			System.out.println("遍历次数："+num);
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 输出存在的书的类型
	 * 过滤器
	 * 效率高于直接上述方法
	 */
	@Test
	public void test05(){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			//基于Filter的过滤方式，可以有效的过滤掉不进行操作的节点，效率会高
			XMLEventReader reader = factory.createFilteredReader(factory.createXMLEventReader(is), 
									new EventFilter() {
										@Override
										public boolean accept(XMLEvent event) {
											//返回true表示会显示，返回false表示不会显示
											if (event.isStartElement()) {
												String name = event.asStartElement().getName().toString();
												if ("title".equals(name)||"price".equals(name)) {
													return true;
												}
											}
											return false;
										}
									});
			int num = 0;
			while (reader.hasNext()) {
				//通过XMLEvent来获取是否是某种节点
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					//通过event.asXxxxx来转换节点
					String name = event.asStartElement().getName().toString();
					if (name.equals("title")) {
						System.out.print(reader.getElementText()+":");
					}
					if (name.equals("price")) {
						System.out.println(reader.getElementText()+":");
					}
				}
				num++;
			}
			System.out.println("遍历次数："+num);
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 输出存在的书的类型
	 * DocumentBuilderFactory
	 * 可以读取部分文档
	 * 效率高于直接全部读取
	 */
	@Test
	public void test06(){
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			//创建文档对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//通过DocumentBuilder创建Document的文档处理对象
			Document doc = db.parse(is);
			//创建xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			//第一个参数是xpath,第二个参数是文档
			NodeList list = (NodeList) xPath.evaluate("//book[@category='WEB']", doc,XPathConstants.NODESET);
			//遍历输出的结果
			int length = list.getLength();
			for (int i=0;i<length;i++) {
				Element e = (Element)list.item(i);
				System.out.println(e.getElementsByTagName("title").item(0).getTextContent());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 创建xml
	 */
	@Test
	public void test07(){
		try {
			XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			//版本号
			xsw.writeStartDocument("UTF-8","1.0");
			xsw.writeEndDocument();
			String ns = "http://aa:dd";
			xsw.writeStartElement("test", "person", ns);
			xsw.writeStartElement(ns,"id");
			xsw.writeCharacters("1");
			xsw.writeEndElement();
			xsw.writeEndElement();
			xsw.flush();
			xsw.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用transform来修改节点
	 */
	@Test
	public void test08(){
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("books.xml");
			//创建文档对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//通过DocumentBuilder创建Document的文档处理对象
			Document doc = db.parse(is);
			//创建xpath
			XPath xPath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			//设置输出属性
			tran.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");
			//第一个参数是xpath,第二个参数是文档
			NodeList list = (NodeList) xPath.evaluate("//book[title='Harry Potter']", doc,XPathConstants.NODESET);
			//获取price节点
			Element book = (Element) list.item(0);
			Element price = (Element) (book.getElementsByTagName("price").item(0));
			price.setTextContent("55555");
			Result result = new StreamResult(System.out);
			//通过transform来修改代码
			tran.transform(new DOMSource(doc), result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
