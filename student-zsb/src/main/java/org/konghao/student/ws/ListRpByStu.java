
package org.konghao.student.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listRpByStu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listRpByStu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xh" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listRpByStu", propOrder = {
    "xh"
})
public class ListRpByStu {

    @XmlElement(required = true)
    protected String xh;

    /**
     * Gets the value of the xh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXh() {
        return xh;
    }

    /**
     * Sets the value of the xh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXh(String value) {
        this.xh = value;
    }

}
