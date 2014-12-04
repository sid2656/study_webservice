package org.konghao.student.service;

import java.util.List;

import javax.inject.Inject;

import org.konghao.basic.model.Pager;
import org.konghao.student.dao.IRewardPunishDao;
import org.konghao.student.dao.IStudentDao;
import org.konghao.student.model.RewardPunish;
import org.konghao.student.model.Student;
import org.konghao.student.model.StudentException;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService implements IStudentService {
	private IStudentDao studentDao;
	private IRewardPunishDao rewardPunishDao;
	
	
	
	public IRewardPunishDao getRewardPunishDao() {
		return rewardPunishDao;
	}
	@Inject
	public void setRewardPunishDao(IRewardPunishDao rewardPunishDao) {
		this.rewardPunishDao = rewardPunishDao;
	}
	public IStudentDao getStudentDao() {
		return studentDao;
	}
	@Inject
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void addStus(List<Student> stus) {
		studentDao.addStus(stus);
	}

	public Pager<Student> find() {
		return studentDao.find("from Student");
	}

	public void update(Student stu) {
		studentDao.update(stu);
	}

	public void delete(int id) {
		List<RewardPunish> rps = rewardPunishDao.listRewardPunishByStu(id);
		if(rps.size()>0) throw new StudentException("学生有相应的奖惩存在，不能删除");
		studentDao.delete(id);
	}

	public boolean checkStuExist(String xh) {
		return studentDao.checkStuExist(xh);
	}

	public List<Student> listRewardStu(Integer year, String semester) {
		return studentDao.listRewardStu(year, semester);
	}

	public List<Student> listPunishStu(Integer year, String semester) {
		return studentDao.listPunishStu(year, semester);
	}
	public Student load(int id) {
		return studentDao.load(id);
	}
	public Student getStudentByXh(String xh) {
		String hql = "select s from Student s where s.xh=?";
		return studentDao.loadByHql(hql, xh);
	}

}
