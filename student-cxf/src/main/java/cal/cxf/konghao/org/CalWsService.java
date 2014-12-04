package cal.cxf.konghao.org;

import javax.jws.WebService;

import org.konghao.ws.User;

@WebService(endpointInterface="cal.cxf.konghao.org.ICalWsService"
			,wsdlLocation="META-INF/wsdl/cal.wsdl",portName="calWsPort",
			serviceName="CalWsService",targetNamespace="http://org.konghao.cxf.cal/")	
public class CalWsService implements ICalWsService {

	public int add(int num1, int num2,User user) {
		System.out.println(user.getUsername()+","+user.getPassword());
		int rel = num1+num2;
		System.out.println(num1+"+"+num2+"="+rel);
		return rel;
	}

}
