package org.zttc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zttc.vo.User;

public class UserDao {
	private static final Map<String,User> users = new HashMap<String, User>();
	private static UserDao dao = null;
	public UserDao(){
		users.put("admin", new User("admin", "admin", "超级管理员"));
		
	}
	public static UserDao newInstence(){
		if (null==dao)dao = new UserDao();
		return dao;
	}

	public void add(User user) throws UserRunTimeException{
		if (users.containsKey(user.getUsername())) {
			 throw new UserRunTimeException("用户已存在");
		}
		users.put(user.getUsername(), user);
	}
	public void delete(String username){
		users.remove(username);
	}
	public List<User> list(){
		Set<String> keys = users.keySet();
		List<User> list = new ArrayList<User>();
		for (String key : keys) {
			list.add(users.get(key));
		}
		return list;
	}
	public User login(String username,String password) throws UserRunTimeException{
		if (!users.containsKey(username)) {
			 throw new UserRunTimeException("用户不存在");
		}
		User u = users.get(username);
		if (!password.equals(u.getPassword())) {
			 throw new UserRunTimeException("用户密码不正确");
		}
		return u;
	}
}
