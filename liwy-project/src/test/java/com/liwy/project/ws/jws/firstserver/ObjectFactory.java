
package com.liwy.project.ws.jws.firstserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.liwy.project.ws.jws.firstserver package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Map2ListResponse_QNAME = new QName("http://jws.ws.project.liwy.com/", "map2ListResponse");
    private final static QName _UpStudentResponse_QNAME = new QName("http://jws.ws.project.liwy.com/", "upStudentResponse");
    private final static QName _SayHelloResponse_QNAME = new QName("http://jws.ws.project.liwy.com/", "sayHelloResponse");
    private final static QName _Map2List_QNAME = new QName("http://jws.ws.project.liwy.com/", "map2List");
    private final static QName _UpStudent_QNAME = new QName("http://jws.ws.project.liwy.com/", "upStudent");
    private final static QName _SayHello_QNAME = new QName("http://jws.ws.project.liwy.com/", "sayHello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.liwy.project.ws.jws.firstserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Map2List }
     * 
     */
    public Map2List createMap2List() {
        return new Map2List();
    }

    /**
     * Create an instance of {@link Map2List.Arg0 }
     * 
     */
    public Map2List.Arg0 createMap2ListArg0() {
        return new Map2List.Arg0();
    }

    /**
     * Create an instance of {@link UpStudent }
     * 
     */
    public UpStudent createUpStudent() {
        return new UpStudent();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link UpStudentResponse }
     * 
     */
    public UpStudentResponse createUpStudentResponse() {
        return new UpStudentResponse();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link Map2ListResponse }
     * 
     */
    public Map2ListResponse createMap2ListResponse() {
        return new Map2ListResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Map2List.Arg0 .Entry }
     * 
     */
    public Map2List.Arg0 .Entry createMap2ListArg0Entry() {
        return new Map2List.Arg0 .Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Map2ListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "map2ListResponse")
    public JAXBElement<Map2ListResponse> createMap2ListResponse(Map2ListResponse value) {
        return new JAXBElement<Map2ListResponse>(_Map2ListResponse_QNAME, Map2ListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "upStudentResponse")
    public JAXBElement<UpStudentResponse> createUpStudentResponse(UpStudentResponse value) {
        return new JAXBElement<UpStudentResponse>(_UpStudentResponse_QNAME, UpStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Map2List }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "map2List")
    public JAXBElement<Map2List> createMap2List(Map2List value) {
        return new JAXBElement<Map2List>(_Map2List_QNAME, Map2List.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "upStudent")
    public JAXBElement<UpStudent> createUpStudent(UpStudent value) {
        return new JAXBElement<UpStudent>(_UpStudent_QNAME, UpStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jws.ws.project.liwy.com/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

}
