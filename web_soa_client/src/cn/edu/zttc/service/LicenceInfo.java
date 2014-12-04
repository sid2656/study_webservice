
package cn.edu.zttc.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for licenceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="licenceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registUser" type="{http://service.zttc.edu.cn}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licenceInfo", propOrder = {
    "registUser"
})
public class LicenceInfo {

    @XmlElement(required = true)
    protected User registUser;

    /**
     * Gets the value of the registUser property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getRegistUser() {
        return registUser;
    }

    /**
     * Sets the value of the registUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setRegistUser(User value) {
        this.registUser = value;
    }

}
