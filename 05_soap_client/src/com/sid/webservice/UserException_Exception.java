
package com.sid.webservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "UserException", targetNamespace = "http://www.sid.com/webservice")
public class UserException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UserException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public UserException_Exception(String message, UserException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public UserException_Exception(String message, UserException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.sid.webservice.UserException
     */
    public UserException getFaultInfo() {
        return faultInfo;
    }

}
