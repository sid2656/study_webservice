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
 * 2012-07-11T17:20:40.312+08:00
 * Generated source version: 2.6.1
 * 
 */
@WebServiceClient(name = "IMyServiceService", 
                  wsdlLocation = "http://localhost:8878/ms?wsdl",
                  targetNamespace = "http://ws.konghao.org/") 
public class IMyServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.konghao.org/", "IMyServiceService");
    public final static QName IMyServicePort = new QName("http://ws.konghao.org/", "IMyServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8878/ms?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IMyServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8878/ms?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public IMyServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IMyServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IMyServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IMyServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IMyServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public IMyServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "IMyServicePort")
    public IMyService getIMyServicePort() {
        return super.getPort(IMyServicePort, IMyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyService
     */
    @WebEndpoint(name = "IMyServicePort")
    public IMyService getIMyServicePort(WebServiceFeature... features) {
        return super.getPort(IMyServicePort, IMyService.class, features);
    }

}