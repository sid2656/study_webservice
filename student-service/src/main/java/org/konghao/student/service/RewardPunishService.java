package org.konghao.student.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.ObjectNotFoundException;
import org.konghao.basic.model.Pager;
import org.konghao.student.dao.IRewardPunishDao;
import org.konghao.student.dao.IStudentDao;
import org.konghao.student.model.RewardPunish;
import org.konghao.student.model.Student;
import org.konghao.student.model.StudentException;
import org.springframework.stereotype.Service;

@Service("rewardPunishService")
public class RewardPunishService implements IRewardPunishService {
	private IRewardPunishDao rewardPunishDao;
	private IStudentDao studentDao;
	
	
	public IStudentDao getStudentDao() {
		return studentDao;
	}
	@Inject
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
	public IRewardPunishDao getRewardPunishDao() {
		return rewardPunishDao;
	}
	@Inject
	public void setRewardPunishDao(IRewardPunishDao rewardPunishDao) {
		this.rewardPunishDao = rewardPunishDao;
	}

	public void add(RewardPunish rp, int stuId) {
		Student stu = studentDao.load(stuId);
		try {
			stu.getName();
		} catch (ObjectNotFoundException e) {
			throw new StudentException("添加奖励的学生不存在");
		}
		rp.setStudent(stu);
		rewardPunishDao.add(rp);
	}

	public void delete(int id) {
		rewardPunishDao.delete(id);
	}

	public void deleteByStu(int stuId) {
		String hql = "delete RewardPunish rp where rp.student.id=?";
		rewardPunishDao.updateByHQL(hql, stuId);
	}

	public List<RewardPunish> listPunishByStu(int stuId) {
		return rewardPunishDao.listPunishByStu(stuId);
	}

	public List<RewardPunish> listRewardByStu(int stuId) {
		return rewardPunishDao.listRewardByStu(stuId);
	}

	public List<RewardPunish> listRewardPunishByStu(int stuId) {
		return rewardPunishDao.listRewardPunishByStu(stuId);
	}
	public RewardPunish load(int id) {
		return rewardPunishDao.load(id);
	}
	public Pager<RewardPunish> findAllRewards() {
		String hql = "select rp from RewardPunish rp left join fetch rp.student where rp.isReward=?";
		return this.rewardPunishDao.find(hql,1);
	}
	public Pager<RewardPunish> findAllPunishs() {
		String hql = "select rp from RewardPunish rp left join fetch rp.student where rp.isReward=?";
		return this.rewardPunishDao.find(hql,0);
	}
	public List<RewardPunish> listRewardPunishByStuXh(String xh) {
		String hql = "select rp from RewardPunish rp left join fetch rp.student stu where stu.xh=?";
		return this.rewardPunishDao.list(hql, xh);
	}

}
