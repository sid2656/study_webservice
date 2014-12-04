package cn.edu.zttc.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.io.FileUtils;
import org.zttc.dao.UserDao;
import org.zttc.dao.UserRunTimeException;
import org.zttc.vo.User;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;

@WebService(endpointInterface = "cn.edu.zttc.service.IUserService", 
			wsdlLocation = "WEB-INF/wsdl/user.wsdl", 
			serviceName = "UserService", 
			portName = "userServicePort", 
			targetNamespace = "http://service.zttc.edu.cn")
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class UserServiceImpl implements IUserService {
	private UserDao userDao = UserDao.newInstence();
	@Resource
	private WebServiceContext ctx;

	@Override
	public void add(User user) throws UserRunTimeException {
		checkRegister();
		userDao.add(user);
	}

	@Override
	public void delete(String username) throws UserRunTimeException {
		checkRegister();
		userDao.delete(username);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public User login(String username, String password)
			throws UserRunTimeException {
		return userDao.login(username, password);
	}

	private void checkRegister() throws UserRunTimeException {
		try {
			HeaderList headers = (HeaderList) ctx.getMessageContext().get(
					JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
			QName qname = new QName("http://service.zttc.edu.cn", "licenceInfo");
			if (headers == null)
				throw new UserRunTimeException("该功能需要进行权限控制");
			Header header = headers.get(qname, true);
			if (header == null)
				throw new UserRunTimeException("该功能需要进行权限控制");
			XMLStreamReader xsr = header.readHeader();
			User u = x2user(xsr);
			User tu = userDao.loadByUsername(u.getUsername());
			if (tu == null)
				throw new UserRunTimeException("你所使用的用户不是系统的授权用户");
			if (!tu.getPassword().equals(u.getPassword()))
				throw new UserRunTimeException("授权用户的密码信息不正确!");
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private User x2user(XMLStreamReader xsr) throws XMLStreamException {
		User u = new User();
		while (xsr.hasNext()) {
			int event = xsr.next();
			if (event == XMLEvent.START_ELEMENT) {
				String name = xsr.getName().toString();
				if (name.equals("username")) {
					u.setUsername(xsr.getElementText());
				} else if (name.equals("password")) {
					u.setPassword(xsr.getElementText());
				} else if (name.equals("nickname")) {
					u.setNickname(xsr.getElementText());
				}
			}
		}
		return u;
	}

	@Override
	public void upload(DataHandler file) {
		FileOutputStream fos = null;
		InputStream fis = null;
		try {
//			System.out.println(file.getContentType() + "--"+ file.getName());
//			fis = file.getInputStream();
			fis = file.getDataSource().getInputStream();
			fos = new FileOutputStream("D:/sid/workspace_webservice/wsimport/1.jpg");
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len=fis.read(buf))>0) {
				fos.write(buf, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
