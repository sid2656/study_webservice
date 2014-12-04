
package cn.edu.zttc.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IUserService", targetNamespace = "http://service.zttc.edu.cn")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IUserService {


    /**
     * 
     * @param user
     * @throws UserRunTimeException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "add", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.AddResponse")
    public void add(
        @WebParam(name = "user", targetNamespace = "")
        User user)
        throws UserRunTimeException_Exception
    ;

    /**
     * 
     * @param username
     * @throws UserRunTimeException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.DeleteResponse")
    public void delete(
        @WebParam(name = "username", targetNamespace = "")
        String username)
        throws UserRunTimeException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<cn.edu.zttc.service.User>
     */
    @WebMethod
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "list", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.List")
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.ListResponse")
    public List<User> list();

    /**
     * 
     * @param username
     * @param password
     * @return
     *     returns cn.edu.zttc.service.User
     * @throws UserRunTimeException_Exception
     */
    @WebMethod
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.zttc.edu.cn", className = "cn.edu.zttc.service.LoginResponse")
    public User login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws UserRunTimeException_Exception
    ;

}
