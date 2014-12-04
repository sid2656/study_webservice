package org.konghao.student.ws.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.konghao.student.ws.Student;
import org.konghao.student.ws.StudentWsService;
import org.konghao.student.ws.IStudentWsService;

public class TestStudent {

	@Test
	public void test01() throws MalformedURLException {
		QName qname = new QName("http://ws.student.konghao.org", "StudentWsService");
		URL url = new URL("http://localhost:7777/stu/ss?wsdl");
		IStudentWsService sws = new StudentWsService().getStudentWsServicePort();
		Student stu = sws.getStudent("201110040137");
		System.out.println(stu);
		System.out.println(stu.getName());
	}
}
