package com.backend.demox.controllers.ws;


import com.backend.demox.models.Element;
import com.backend.demox.services.ElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

// http://localhost:8082/demox/elements/ws/elementWsService?wsdl

@Service
@WebService(name = "ElementWsService", serviceName = "ElementWsService", targetNamespace = "http://rafx.show.org.pl/ws")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@Slf4j
public class ElementWsService {

    @Autowired
    private ElementService elementService;

    @WebMethod(operationName = "GetInfo")
    public String GetInfo() {
        return "WebService Result";
    }

    @WebMethod(operationName = "GetSqr")
    public Integer GetInfo(Integer i) {
        return  i*i;
    }


    @WebMethod(operationName = "GetElementById")
    @WebResult(name = "Element", targetNamespace = "http://rafx.show.org.pl/ws")
    public Element GetElementById(
            @WebParam(name = "ElementId",
                      targetNamespace = "http://rafx.show.org.pl/ws") Long elementId ) {
        return elementService.GetOneElement(elementId);
       }
}
