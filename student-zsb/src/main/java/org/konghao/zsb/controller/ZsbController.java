package org.konghao.zsb.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.konghao.student.ws.IStudentWsService;
import org.konghao.student.ws.RewardPunish;
import org.konghao.student.ws.Student;
import org.konghao.student.ws.StudentWsException_Exception;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ZsbController {
	private IStudentWsService studentWsService;
	

	public IStudentWsService getStudentWsService() {
		return studentWsService;
	}
	@Inject
	public void setStudentWsService(IStudentWsService studentWsService) {
		this.studentWsService = studentWsService;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session) {
		session.setAttribute("loginUser", "admin");
		return "add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(String xh,String name,String school,Model model) {
		//这种方式不好
		//IStudentWsService sws = new StudentWsService().getStudentWsServicePort();
		System.out.println(studentWsService);
		Student stu = null;
		try {
			stu = studentWsService.getStudent(xh);
		} catch (StudentWsException_Exception e) {
			model.addAttribute("error", e.getMessage());
			return "add";
		}
		if(stu==null) {
			model.addAttribute("error", "该学生不是我校学生，请检查输入");
			return "add";
		}
		int status = stu.getStatus();
		if(status==1) {
			model.addAttribute("error", "该生不是在校生，无法报名");
			return "add";
		}
		List<RewardPunish> list = studentWsService.listRpByStu(xh);
		for(RewardPunish rp:list) {
			if(rp.getIsReward()==0) {
				model.addAttribute("error", "该生有相应的处分信息，无法报名");
				return "add";
			}
		}
		model.addAttribute("xh", xh);
		model.addAttribute("name", stu.getName());
		model.addAttribute("school", school);
		return "success";
	}
}
