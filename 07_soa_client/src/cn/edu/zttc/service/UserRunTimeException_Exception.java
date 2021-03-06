
package cn.edu.zttc.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "UserRunTimeException", targetNamespace = "http://service.zttc.edu.cn")
public class UserRunTimeException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UserRunTimeException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public UserRunTimeException_Exception(String message, UserRunTimeException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public UserRunTimeException_Exception(String message, UserRunTimeException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: cn.edu.zttc.service.UserRunTimeException
     */
    public UserRunTimeException getFaultInfo() {
        return faultInfo;
    }

}
