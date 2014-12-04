package org.konghao.student.service;

import java.util.List;

import org.konghao.basic.model.Pager;
import org.konghao.student.model.Student;

public interface IStudentService {
	public void addStus(List<Student> stus);
	public Pager<Student> find();
	public void update(Student stu);
	public void delete(int id);
	public Student load(int id);
	/**
	 * 根据学学号检查学生用户是否存在
	 * @param xh
	 * @return
	 */
	public boolean checkStuExist(String xh);
	/**
	 * 通过学期和学年获取所有的奖励的学生
	 * @param year 学年 :如果为空，表示不获取学年
	 * @param semester 学期 如果为空表示不获取学期
	 * @return
	 */
	public List<Student> listRewardStu(Integer year,String semester);


	/**
	 * 通过学期和学年获取所有的处罚的学生
	 * @param year 学年 :如果为空，表示不获取学年
	 * @param semester 学期 如果为空表示不获取学期
	 * @return
	 */
	public List<Student> listPunishStu(Integer year,String semester);
	
	public Student getStudentByXh(String xh);
}
