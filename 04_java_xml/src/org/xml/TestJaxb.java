package org.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class TestJaxb {
	@Test
	public void test01(){
		try {
			JAXBContext ctx = JAXBContext.newInstance(Student.class);
			//编排：把对象转换成xml
			Marshaller marshaller = ctx.createMarshaller();
			Student stu = new Student(1, "zhangsan", 21, new Classroom(1, "计算机", 2010));
			marshaller.marshal(stu, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test02(){
		try {
			JAXBContext ctx = JAXBContext.newInstance(Student.class);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>21</age><classroom><grade>2010</grade><id>1</id><name>计算机</name></classroom><id>1</id><name>zhangsan</name></student>";
			//编排：把对象转换成xml
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			Student stu = (Student) unmarshaller.unmarshal(new StringReader(xml));
			System.out.println(stu.getName()+stu.getClassroom().getName());;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
