package org.konghao.student.dao;

import java.util.List;

import org.konghao.basic.peresist.BaseDao;
import org.konghao.student.model.Student;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao extends BaseDao<Student> implements IStudentDao {

	public boolean checkStuExist(String xh) {
		String hql = "select count(*) from Student stu where stu.xh=?";
		Long count = (Long) this.loadObjByHQL(hql,xh);
		if(count>0) return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private List<String> listAllKsh() {
		String hql = "select ksh from Student";
		return this.getSession().createQuery(hql).list();
	}

	public void addStus(List<Student> stus) {
		int index = 0;
		List<String> kshs = listAllKsh();
		for(Student stu:stus) {
			if(kshs.contains(stu.getKsh())) continue;
			this.add(stu);
			if(index++==20) {
				this.getSession().clear();
				index = 0;
			}
		}
	}

	public List<Student> listRewardStu(Integer year, String semester) {
		String hql = "select stu from RewardPunish rp join rp.student stu where rp.isReward=1";
		if(year!=null&&year>0) {
			hql+=" and rp.year="+year;
		}
		if(semester!=null&&!"".equals(semester)) {
			hql+=" and rp.semester="+semester;
		}
		return this.list(hql);
	}

	public List<Student> listPunishStu(Integer year, String semester) {
		String hql = "select stu from RewardPunish rp join rp.student stu where rp.isReward=0";
		if(year!=null&&year>0) {
			hql+=" and rp.year="+year;
		}
		if(semester!=null&&!"".equals(semester)) {
			hql+=" and rp.semester="+semester;
		}
		return this.list(hql);
	}

}
