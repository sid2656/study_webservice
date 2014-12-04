package org.konghao.student.web;

public class LoginUserContext {
	private static ThreadLocal<String> loginUser = new ThreadLocal<String>();
	
	public static String getLoginUser() {
		return loginUser.get();
	}
	
	public static void setLoginUser(String user) {
		loginUser.set(user);
	}
	
	public static void removeLoginUser() {
		loginUser.remove();
	}
}
