package org.konghao.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_reward_punish")
public class RewardPunish {
	private int id;
	private String situation;
	private int year;
	private String semester;
	private String cause;
	private Student student;
	//是否是奖励，1表示是，0表示不是
	private int isReward;
	
	
	@Column(name="is_reward")
	public int getIsReward() {
		return isReward;
	}
	public void setIsReward(int isReward) {
		this.isReward = isReward;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@ManyToOne
	@JoinColumn(name="stu_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
