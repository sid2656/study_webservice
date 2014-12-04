package org.konghao.student.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.konghao.poi.util.ExcelUtil;
import org.konghao.student.dto.StudentExcelDto;
import org.konghao.student.model.RewardPunish;
import org.konghao.student.model.Student;
import org.konghao.student.report.IReportService;
import org.konghao.student.service.IRewardPunishService;
import org.konghao.student.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/stu")
public class StudentController {
	private IStudentService studentService;
	private IReportService reportService;
	private IRewardPunishService rewardPunishService;
	
	public IRewardPunishService getRewardPunishService() {
		return rewardPunishService;
	}
	@Inject
	public void setRewardPunishService(IRewardPunishService rewardPunishService) {
		this.rewardPunishService = rewardPunishService;
	}
	public IReportService getReportService() {
		return reportService;
	}
	@Inject
	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	public IStudentService getStudentService() {
		return studentService;
	}
	@Inject
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping("/stus")
	public String list(Model model) {
		model.addAttribute("stus", studentService.find());
		return "stu/list";
	}
	
	@RequestMapping(value="import",method=RequestMethod.GET)
	public String importStu() {
		return "stu/import";
	}
	@RequestMapping(value="import",method=RequestMethod.POST)
	public String importStu(@RequestParam MultipartFile stuList,Model model,HttpServletRequest req) {
		String name = stuList.getOriginalFilename();
		String type = FilenameUtils.getExtension(name);
		if(!type.equalsIgnoreCase("xls")&&!type.equalsIgnoreCase("xlsx")) {
			model.addAttribute("error", "传入的类型不正确");
			return "stu/import";
		}
		try {
			String path = req.getSession()
					.getServletContext().getRealPath("/xls/"+name);
			FileUtils.copyInputStreamToFile(stuList.getInputStream(),new File(path));
			List<Object> seds = ExcelUtil.getInstance().readExcel2ObjsByPath(path, StudentExcelDto.class,1,0);
			List<Student> stus = StudentExcelDto.getStus(seds);
			studentService.addStus(stus);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/stu/stus";
	}
	
	@RequestMapping(value="export")
	public void export(HttpServletResponse resp) throws IOException {
		List<StudentExcelDto> seds = reportService.getReportStus();
		Map<String,String> datas = new HashMap<String,String>();
		datas.put("title","学生基本信息");
		datas.put("total", String.valueOf(seds.size()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		datas.put("date", sdf.format(new Date()));
		resp.setContentType("application/vnd.ms-excel");
		String name = "学生基本信息";
		resp.setHeader("Content-Disposition","attachment;filename = "+
		new String(name.getBytes("GBK"),"ISO8859-1")+".xls");
		ExcelUtil.getInstance().exportObj2ExcelByTemplate(datas, "/template/stu.xls", 
				resp.getOutputStream(), seds, StudentExcelDto.class, true);
//		ExcelUtil.getInstance().exportObj2Excel(resp.getOutputStream(), seds, StudentExcelDto.class, false);
	}
	
	@RequestMapping(value="addReward/{id}",method=RequestMethod.GET)
	public String addReward(@PathVariable int id,Model model) {
		model.addAttribute("stu", studentService.load(id));
		model.addAttribute("rp", new RewardPunish());
		return "stu/addReward";
	}
	
	@RequestMapping(value="addReward/{id}",method=RequestMethod.POST)
	public String addReward(@PathVariable int id,RewardPunish rp) {
		rp.setIsReward(1);
		rewardPunishService.add(rp, id);
		return "redirect:/stu/stus";
	}
	
	@RequestMapping(value="addPunish/{id}",method=RequestMethod.GET)
	public String addPunish(@PathVariable int id,Model model) {
		model.addAttribute("stu", studentService.load(id));
		model.addAttribute("rp", new RewardPunish());
		return "stu/addPunish";
	}
	
	@RequestMapping(value="addPunish/{id}",method=RequestMethod.POST)
	public String addPunish(@PathVariable int id,RewardPunish rp) {
		rp.setIsReward(0);
		rewardPunishService.add(rp, id);
		return "redirect:/stu/stus";
	}
	
	@RequestMapping("rewards")
	public String listRewards(Model model) {
		model.addAttribute("rps", rewardPunishService.findAllRewards());
		return "stu/rewards";
	}
	
	@RequestMapping("punishs")
	public String listPunishs(Model model) {
		model.addAttribute("rps", rewardPunishService.findAllPunishs());
		return "stu/punishs";
	}
	
	@RequestMapping(value="update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		Student stu = studentService.load(id);
		model.addAttribute("stu", stu);
		return "stu/update";
	}
	
	@RequestMapping(value="update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Student stu) {
		Student ts = studentService.load(id);
		ts.setKsh(stu.getKsh());
		ts.setName(stu.getName());
		ts.setSex(stu.getSex());
		ts.setStatus(stu.getStatus());
		ts.setXh(stu.getXh());
		studentService.update(ts);
		return "redirect:/stu/stus";
	}
	
	@RequestMapping("rp/{id}")
	public String showRps(@PathVariable int id,Model model) {
		model.addAttribute("ps", rewardPunishService.listPunishByStu(id));
		model.addAttribute("rs", rewardPunishService.listRewardByStu(id));
		model.addAttribute("stu", studentService.load(id));
		return "stu/rp";
	}
}
