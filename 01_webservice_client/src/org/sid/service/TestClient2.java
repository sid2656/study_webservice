package org.sid.service;


public class TestClient2 {
	public static void main(String[] args) {
		MyServiceImplService msis = new MyServiceImplService();
		IMyService ms = msis.getMyServiceImplPort();
		ms.add(12, 2);
	}
}
