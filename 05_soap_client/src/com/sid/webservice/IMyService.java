
package com.sid.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IMyService", targetNamespace = "http://www.sid.com/webservice")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IMyService {


    /**
     * 
     * @param b
     * @param a
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "add", targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.AddResponse")
    @Action(input = "http://www.sid.com/webservice/IMyService/addRequest", output = "http://www.sid.com/webservice/IMyService/addResponse")
    public int add(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

    /**
     * 
     * @param authInfo
     * @param parameters
     * @return
     *     returns com.sid.webservice.ListResponse
     */
    @WebMethod
    @WebResult(name = "listResponse", targetNamespace = "http://www.sid.com/webservice", partName = "result")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "http://www.sid.com/webservice/IMyService/listRequest", output = "http://www.sid.com/webservice/IMyService/listResponse")
    public ListResponse list(
        @WebParam(name = "list", targetNamespace = "http://www.sid.com/webservice", partName = "parameters")
        List parameters,
        @WebParam(name = "authInfo", targetNamespace = "http://www.sid.com/webservice", header = true, partName = "authInfo")
        String authInfo);

    /**
     * 
     * @param user
     */
    @WebMethod
    @RequestWrapper(localName = "addUser", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.AddUserResponse")
    @Action(input = "http://www.sid.com/webservice/IMyService/addUserRequest", output = "http://www.sid.com/webservice/IMyService/addUserResponse")
    public void addUser(
        @WebParam(name = "user", targetNamespace = "http://www.sid.com/webservice", mode = WebParam.Mode.INOUT)
        Holder<User> user);

    /**
     * 
     * @param username
     * @param password
     * @return
     *     returns com.sid.webservice.User
     * @throws UserException_Exception
     */
    @WebMethod
    @WebResult(name = "user", targetNamespace = "http://www.sid.com/webservice")
    @RequestWrapper(localName = "login", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://www.sid.com/webservice", className = "com.sid.webservice.LoginResponse")
    @Action(input = "http://www.sid.com/webservice/IMyService/loginRequest", output = "http://www.sid.com/webservice/IMyService/loginResponse", fault = {
        @FaultAction(className = UserException_Exception.class, value = "http://www.sid.com/webservice/IMyService/login/Fault/UserException")
    })
    public User login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws UserException_Exception
    ;

}
