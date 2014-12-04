package org.konghao.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2012-07-11T16:42:40.421+08:00
 * Generated source version: 2.6.1
 * 
 */
@WebServiceClient(name = "MyServiceService", 
                  wsdlLocation = "http://localhost:8878/ms?wsdl",
                  targetNamespace = "http://ws.konghao.org/") 
public class MyServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.konghao.org/", "MyServiceService");
    public final static QName MyServicePort = new QName("http://ws.konghao.org/", "MyServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8878/ms?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(MyServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8878/ms?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MyServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MyServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MyServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MyServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public MyServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort() {
        return super.getPort(MyServicePort, IMyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "MyServicePort")
    public IMyService getMyServicePort(WebServiceFeature... features) {
        return super.getPort(MyServicePort, IMyService.class, features);
    }

}