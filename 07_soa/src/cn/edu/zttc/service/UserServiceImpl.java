package cn.edu.zttc.service;

import java.util.List;

import javax.jws.WebService;

import org.zttc.dao.UserDao;
import org.zttc.dao.UserRunTimeException;
import org.zttc.vo.User;

@WebService(endpointInterface="cn.edu.zttc.service.IUserService",
			wsdlLocation="META-INF/wsdl/user.wsdl",
			serviceName="UserService",
			portName="userServicePort",
			targetNamespace="http://service.zttc.edu.cn")
public class UserServiceImpl implements IUserService {
	private UserDao userDao = UserDao.newInstence();
	@Override
	public void add(User user) throws UserRunTimeException {
		userDao.add(user);
	}

	@Override
	public void delete(String username) {
		userDao.delete(username);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public User login(String username, String password) throws UserRunTimeException {
		return userDao.login(username, password);
	}
}
