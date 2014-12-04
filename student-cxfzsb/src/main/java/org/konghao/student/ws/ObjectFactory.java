
package org.konghao.student.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.konghao.student.ws package. 
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

    private final static QName _StudentWsException_QNAME = new QName("http://ws.student.konghao.org", "StudentWsException");
    private final static QName _ListRpByStu_QNAME = new QName("http://ws.student.konghao.org", "listRpByStu");
    private final static QName _ListPunishStuResponse_QNAME = new QName("http://ws.student.konghao.org", "listPunishStuResponse");
    private final static QName _ListRewardStuResponse_QNAME = new QName("http://ws.student.konghao.org", "listRewardStuResponse");
    private final static QName _GetStudentResponse_QNAME = new QName("http://ws.student.konghao.org", "getStudentResponse");
    private final static QName _ListPunishStu_QNAME = new QName("http://ws.student.konghao.org", "listPunishStu");
    private final static QName _GetStudent_QNAME = new QName("http://ws.student.konghao.org", "getStudent");
    private final static QName _LicenseInfo_QNAME = new QName("http://ws.student.konghao.org", "licenseInfo");
    private final static QName _ListRewardStu_QNAME = new QName("http://ws.student.konghao.org", "listRewardStu");
    private final static QName _ListRpByStuResponse_QNAME = new QName("http://ws.student.konghao.org", "listRpByStuResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.konghao.student.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RewardPunish }
     * 
     */
    public RewardPunish createRewardPunish() {
        return new RewardPunish();
    }

    /**
     * Create an instance of {@link ListRewardStuResponse }
     * 
     */
    public ListRewardStuResponse createListRewardStuResponse() {
        return new ListRewardStuResponse();
    }

    /**
     * Create an instance of {@link GetStudentResponse }
     * 
     */
    public GetStudentResponse createGetStudentResponse() {
        return new GetStudentResponse();
    }

    /**
     * Create an instance of {@link ListRpByStuResponse }
     * 
     */
    public ListRpByStuResponse createListRpByStuResponse() {
        return new ListRpByStuResponse();
    }

    /**
     * Create an instance of {@link GetStudent }
     * 
     */
    public GetStudent createGetStudent() {
        return new GetStudent();
    }

    /**
     * Create an instance of {@link ListPunishStu }
     * 
     */
    public ListPunishStu createListPunishStu() {
        return new ListPunishStu();
    }

    /**
     * Create an instance of {@link ListRpByStu }
     * 
     */
    public ListRpByStu createListRpByStu() {
        return new ListRpByStu();
    }

    /**
     * Create an instance of {@link StudentWsException }
     * 
     */
    public StudentWsException createStudentWsException() {
        return new StudentWsException();
    }

    /**
     * Create an instance of {@link ListPunishStuResponse }
     * 
     */
    public ListPunishStuResponse createListPunishStuResponse() {
        return new ListPunishStuResponse();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link ListRewardStu }
     * 
     */
    public ListRewardStu createListRewardStu() {
        return new ListRewardStu();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentWsException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "StudentWsException")
    public JAXBElement<StudentWsException> createStudentWsException(StudentWsException value) {
        return new JAXBElement<StudentWsException>(_StudentWsException_QNAME, StudentWsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRpByStu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listRpByStu")
    public JAXBElement<ListRpByStu> createListRpByStu(ListRpByStu value) {
        return new JAXBElement<ListRpByStu>(_ListRpByStu_QNAME, ListRpByStu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPunishStuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listPunishStuResponse")
    public JAXBElement<ListPunishStuResponse> createListPunishStuResponse(ListPunishStuResponse value) {
        return new JAXBElement<ListPunishStuResponse>(_ListPunishStuResponse_QNAME, ListPunishStuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRewardStuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listRewardStuResponse")
    public JAXBElement<ListRewardStuResponse> createListRewardStuResponse(ListRewardStuResponse value) {
        return new JAXBElement<ListRewardStuResponse>(_ListRewardStuResponse_QNAME, ListRewardStuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "getStudentResponse")
    public JAXBElement<GetStudentResponse> createGetStudentResponse(GetStudentResponse value) {
        return new JAXBElement<GetStudentResponse>(_GetStudentResponse_QNAME, GetStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPunishStu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listPunishStu")
    public JAXBElement<ListPunishStu> createListPunishStu(ListPunishStu value) {
        return new JAXBElement<ListPunishStu>(_ListPunishStu_QNAME, ListPunishStu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "getStudent")
    public JAXBElement<GetStudent> createGetStudent(GetStudent value) {
        return new JAXBElement<GetStudent>(_GetStudent_QNAME, GetStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "licenseInfo")
    public JAXBElement<String> createLicenseInfo(String value) {
        return new JAXBElement<String>(_LicenseInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRewardStu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listRewardStu")
    public JAXBElement<ListRewardStu> createListRewardStu(ListRewardStu value) {
        return new JAXBElement<ListRewardStu>(_ListRewardStu_QNAME, ListRewardStu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRpByStuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.student.konghao.org", name = "listRpByStuResponse")
    public JAXBElement<ListRpByStuResponse> createListRpByStuResponse(ListRpByStuResponse value) {
        return new JAXBElement<ListRpByStuResponse>(_ListRpByStuResponse_QNAME, ListRpByStuResponse.class, null, value);
    }

}
