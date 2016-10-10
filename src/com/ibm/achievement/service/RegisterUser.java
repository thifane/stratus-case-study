//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.ibm.achievement.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeVO" type="{http://service.achievement.ibm.com/}employeeVO" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUser", namespace = "http://service.achievement.ibm.com/", propOrder = {
    "employeeVO",
    "password"
})
public class RegisterUser {

    protected EmployeeVO employeeVO;
    protected String password;

    /**
     * Gets the value of the employeeVO property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeVO }
     *     
     */
    public EmployeeVO getEmployeeVO() {
        return employeeVO;
    }

    /**
     * Sets the value of the employeeVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeVO }
     *     
     */
    public void setEmployeeVO(EmployeeVO value) {
        this.employeeVO = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
