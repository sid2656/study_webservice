package org.konghao.student.dto;

import java.util.ArrayList;
import java.util.List;

import org.konghao.poi.util.ExcelResources;
import org.konghao.student.model.Student;

public class StudentExcelDto {
	
	private String name;
	private String xh;
	private String ksh;
	private String xb;
	private String status;
	
	@ExcelResources(title="姓名",order=2)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ExcelResources(title="学号",order=1)
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	@ExcelResources(title="考生号")
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	@ExcelResources(title="性别",order=4)
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	@ExcelResources(title="当前状态",order=3)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static List<Student> getStus(List<Object> stus) {
		List<Student> ss = new ArrayList<Student>();
		for(Object obj:stus) {
			StudentExcelDto sed = (StudentExcelDto)obj;
			ss.add(sed.getStu());
		}
		return ss;
	}
	
	public Student getStu() {
		Student stu = new Student();
		stu.setKsh(this.getKsh());
		stu.setName(this.getName());
		stu.setSex(this.getXb());
		stu.setXh(this.getXh());
		if(this.getStatus().equals("在校")) {
			stu.setStatus(0);
		} else {
			stu.setStatus(1);
		}
		return stu;
	}
}
