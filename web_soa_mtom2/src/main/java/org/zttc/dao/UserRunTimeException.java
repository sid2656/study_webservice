package org.zttc.dao;

/**
 * 继承RunTimeException会在服务器端抛出异常
 * 所以要继承为Exception
 */
public class UserRunTimeException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserRunTimeException() {
		super();
	}

	public UserRunTimeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UserRunTimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserRunTimeException(String arg0) {
		super(arg0);
	}

	public UserRunTimeException(Throwable arg0) {
		super(arg0);
	}

}
