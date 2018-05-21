
package com.liwy.project.ws.jws.firstserver;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FirstService", targetNamespace = "http://jws.ws.project.liwy.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FirstService {


    /**
     * 
     * @param meName
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.SayHelloResponse")
    @Action(input = "http://jws.ws.project.liwy.com/FirstService/sayHelloRequest", output = "http://jws.ws.project.liwy.com/FirstService/sayHelloResponse")
    public String sayHello(
        @WebParam(name = "me-name", targetNamespace = "")
        String meName);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "map2List", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.Map2List")
    @ResponseWrapper(localName = "map2ListResponse", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.Map2ListResponse")
    @Action(input = "http://jws.ws.project.liwy.com/FirstService/map2ListRequest", output = "http://jws.ws.project.liwy.com/FirstService/map2ListResponse")
    public List<String> map2List(
        @WebParam(name = "arg0", targetNamespace = "")
        com.liwy.project.ws.jws.firstserver.Map2List.Arg0 arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.liwy.project.ws.jws.firstserver.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "upStudent", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.UpStudent")
    @ResponseWrapper(localName = "upStudentResponse", targetNamespace = "http://jws.ws.project.liwy.com/", className = "com.liwy.project.ws.jws.firstserver.UpStudentResponse")
    @Action(input = "http://jws.ws.project.liwy.com/FirstService/upStudentRequest", output = "http://jws.ws.project.liwy.com/FirstService/upStudentResponse")
    public User upStudent(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

}