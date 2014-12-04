package org.konghao.student.dao;

import java.util.List;

import org.konghao.basic.peresist.IBaseDao;
import org.konghao.student.model.RewardPunish;

public interface IRewardPunishDao extends IBaseDao<RewardPunish>{
	/**
	 * 通过学生id获取该学生的所有处罚情况
	 * @param stuId 学生id
	 * @return
	 */
	public List<RewardPunish> listPunishByStu(int stuId);
	/**
	 * 通过学生id获取该学生的所有奖励情况
	 * @param stuId
	 * @return
	 */
	public List<RewardPunish> listRewardByStu(int stuId);
	/**
	 * 获取学生的所有奖惩情况
	 * @param stuId
	 * @return
	 */
	public List<RewardPunish> listRewardPunishByStu(int stuId);
}
