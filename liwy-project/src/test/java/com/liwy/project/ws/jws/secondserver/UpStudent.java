
package com.liwy.project.ws.jws.secondserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>upStudent complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="upStudent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="student" type="{http://second.jws.ws.project.liwy.com/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "upStudent", propOrder = {
    "student"
})
public class UpStudent {

    protected User student;

    /**
     * ��ȡstudent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getStudent() {
        return student;
    }

    /**
     * ����student���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setStudent(User value) {
        this.student = value;
    }

}
