package org.konghao.student.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.konghao.basic.model.SystemContext;
import org.konghao.student.model.RewardPunish;
import org.konghao.student.model.Student;
import org.konghao.student.model.StudentException;
import org.konghao.student.service.IRewardPunishService;
import org.konghao.student.service.IStudentService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
@TransactionConfiguration(transactionManager="txManager",defaultRollback=true)
@Transactional
public class TestStudent {
	@Inject
	private IStudentService studentService;
	@Inject
	private IRewardPunishService rewardPunishService;
	
	private int[] add() {
		Student stu1 = new Student();
		stu1.setKsh("123");
		stu1.setName("张三");
		stu1.setSex("男");
		stu1.setStatus(0);
		stu1.setXh("22111");
		List<Student> stus = new ArrayList<Student>();
		stus.add(stu1);
		Student stu = new Student();
		stu.setKsh("2222");
		stu.setName("李四");
		stu.setSex("女");
		stu.setStatus(0);
		stu.setXh("22222222");
		stus.add(stu);
		studentService.addStus(stus);
		RewardPunish rp = new RewardPunish();
		rp.setCause("打架");
		rp.setIsReward(1);
		rp.setSemester("秋季");
		rp.setYear(2011);
		rp.setSituation("留校察看");
		rewardPunishService.add(rp, stu1.getId());
		int[] ids = new int[]{stu.getId(),stu1.getId()};
		return ids;
	}
	
	@Test
	public void testAdd() {
		SystemContext.setPageSize(100);
		SystemContext.setPageOffset(0);
		add();
		List<Student> ss = studentService.find().getDatas();
		
	}
	
	@Test
	public void testLoad() {
		int[] ids = add();
		Student stu = studentService.load(ids[0]);
		Assert.assertEquals(stu.getName(), "李四");
	}
	@Test
	public void testDelete() {
		int[] ids = add();
		studentService.delete(ids[0]);
		List<Student> ss = studentService.find().getDatas();
	}
	
	@Test(expected=StudentException.class)
	public void testDeleteHasReward() {
		int[] ids = add();
		studentService.delete(ids[1]);
		List<Student> ss = studentService.find().getDatas();
		Assert.assertEquals(ss.size(), 1);
	}
	
	@Test(expected=StudentException.class)
	public void testAddRpNotStuent() {
		RewardPunish rp = new RewardPunish();
		rewardPunishService.add(rp, 999);
	}
}
