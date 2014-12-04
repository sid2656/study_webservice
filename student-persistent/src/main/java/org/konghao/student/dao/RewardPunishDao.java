package org.konghao.student.dao;

import java.util.List;

import org.konghao.basic.peresist.BaseDao;
import org.konghao.student.model.RewardPunish;
import org.springframework.stereotype.Repository;


@Repository("rewardPunishDao")
public class RewardPunishDao extends BaseDao<RewardPunish> implements
		IRewardPunishDao {

	public List<RewardPunish> listPunishByStu(int stuId) {
		String hql = "select rp from RewardPunish rp where rp.student.id=? and isReward=0";
		return this.list(hql, stuId);
	}

	public List<RewardPunish> listRewardByStu(int stuId) {
		String hql = "select rp from RewardPunish rp where rp.student.id=? and isReward=1";
		return this.list(hql, stuId);
	}

	public List<RewardPunish> listRewardPunishByStu(int stuId) {
		String hql = "select rp from RewardPunish rp where rp.student.id=?";
		return this.list(hql, stuId);
	}


}
