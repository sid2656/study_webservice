package org.konghao.student.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.konghao.student.model.RewardPunish;
import org.konghao.student.model.Student;
import org.konghao.student.service.IRewardPunishService;
import org.konghao.student.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * 使用这种方式公布出来的服务，默认是不被Spring所管理的，所以依赖注入无法生效
 * @author Administrator
 *
 */
@WebService(endpointInterface="org.konghao.student.ws.IStudentWsService",
			serviceName="StudentWsService",
			targetNamespace="http://ws.student.konghao.org",
			portName="StudentWsServicePort",
			wsdlLocation="/WEB-INF/wsdl/student.wsdl")
@Service("studentWsService")
public class StudentWsService implements IStudentWsService {
	//希望通过Servier获取HttpServlet的API需要先注入WebServiceContext
//	@Resource
//	private WebServiceContext wsc;
	
	private IStudentService studentService;
	private IRewardPunishService rewardPunishService;
	
	
	public IStudentService getStudentService() {
		return studentService;
	}
	@Inject
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IRewardPunishService getRewardPunishService() {
		return rewardPunishService;
	}
	@Inject
	public void setRewardPunishService(IRewardPunishService rewardPunishService) {
		this.rewardPunishService = rewardPunishService;
	}

	@Override
	public Student getStudent(String xh,String licenseInfo) throws StudentWsException {
//		MessageContext ctx = wsc.getMessageContext();
		//获取HttpServletRequest
//		HttpServletRequest req = (HttpServletRequest)ctx.get(SOAPMessageContext.SERVLET_REQUEST);
//		System.out.println(req.getSession().getServletContext().getRealPath("/"));
		if(licenseInfo==null||"".equals(licenseInfo.trim())) {
			System.out.println("in");
			throw new StudentWsException("必须有验证的用户才能使用该功能");
		}
		if(!licenseInfo.equals("admin")) throw new StudentWsException("验证用户必须为admin");
		Student stu = studentService.getStudentByXh(xh);
		return stu;
	}

	@Override
	public List<RewardPunish> listRpByStu(String xh) {
		return rewardPunishService.listRewardPunishByStuXh(xh);
	}

	@Override
	public List<Student> listRewardStu(int year, String semester) {
		return studentService.listRewardStu(year, semester);
	}

	@Override
	public List<Student> listPunishStu(int year, String semester) {
		return studentService.listPunishStu(year, semester);
	}

}
