
package org.konghao.student.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rewardPunish complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rewardPunish">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="situation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="semester" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cause" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isReward" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="student" type="{http://ws.student.konghao.org}student"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rewardPunish", propOrder = {
    "id",
    "situation",
    "year",
    "semester",
    "cause",
    "isReward",
    "student"
})
public class RewardPunish {

    protected int id;
    @XmlElement(required = true)
    protected String situation;
    protected int year;
    @XmlElement(required = true)
    protected String semester;
    @XmlElement(required = true)
    protected String cause;
    protected int isReward;
    @XmlElement(required = true)
    protected Student student;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the situation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituation() {
        return situation;
    }

    /**
     * Sets the value of the situation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituation(String value) {
        this.situation = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the semester property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the value of the semester property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSemester(String value) {
        this.semester = value;
    }

    /**
     * Gets the value of the cause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCause() {
        return cause;
    }

    /**
     * Sets the value of the cause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCause(String value) {
        this.cause = value;
    }

    /**
     * Gets the value of the isReward property.
     * 
     */
    public int getIsReward() {
        return isReward;
    }

    /**
     * Sets the value of the isReward property.
     * 
     */
    public void setIsReward(int value) {
        this.isReward = value;
    }

    /**
     * Gets the value of the student property.
     * 
     * @return
     *     possible object is
     *     {@link Student }
     *     
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the value of the student property.
     * 
     * @param value
     *     allowed object is
     *     {@link Student }
     *     
     */
    public void setStudent(Student value) {
        this.student = value;
    }

}
