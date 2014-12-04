package org.konghao.student.service;

import java.util.List;

import org.konghao.basic.model.Pager;
import org.konghao.student.model.RewardPunish;

public interface IRewardPunishService {
	/**
	 * 通过学生id添加相应的奖惩信息
	 * @param rp
	 * @param stuId
	 */
	public void add(RewardPunish rp,int stuId);
	/**
	 * 通过奖惩的id删除奖惩信息
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 通过学生id删除奖惩信息
	 * @param stuId
	 */
	public void deleteByStu(int stuId);
	
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
	
	/**
	 * 获取学生的所有奖惩情况
	 * @param stuId
	 * @return
	 */
	public List<RewardPunish> listRewardPunishByStuXh(String xh);
	
	public RewardPunish load(int id);
	/**
	 * 获取所有的奖励学生
	 * @return
	 */
	public Pager<RewardPunish> findAllRewards();
	
	/**
	 * 获取所有的处罚学生
	 * @return
	 */
	public Pager<RewardPunish> findAllPunishs();
}
