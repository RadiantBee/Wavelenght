package com.backend.wavelenght.configuration;

import com.backend.wavelenght.controllers.ws.ElementWsService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.soap.SOAPBinding;

@Configuration
public class WebServicesConfiguration {

    @Bean
    public ServletRegistrationBean<CXFServlet> cxfServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/elements/ws/*");
    }

    @Bean(initMethod = "publish", destroyMethod = "stop")
    public Endpoint authenticationServiceEndpoint(Bus cxfBus,
                                                  ElementWsService elementWsService) {

        // EndpointImpl endpoint = new EndpointImpl(cxfBus, elementWsService, SOAPBinding.SOAP12HTTP_MTOM_BINDING);
        EndpointImpl endpoint = new EndpointImpl(cxfBus, elementWsService, SOAPBinding.SOAP12HTTP_BINDING);
        endpoint.setAddress("/elementWsService");

        //endpoint.getFeatures().add(new MtomFeature());
        endpoint.setPublishedEndpointUrl("/elements/ws/elementWsService");
        return endpoint;
    }

}
