package com.backend.wavelenght.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.Thread.currentThread;


@RestController
@RequestMapping("/infos")
public class SimpleController {

    private static Logger LOG = LoggerFactory.getLogger(SimpleController.class);


    // the simplest

    @GetMapping("/test")
    public String GetInfo(){
        String mess ="Test - OK";
        LOG.info(mess);
        return  mess;
    }


    private static final String nullmsg ="none";

    // echo
    // Using RequestParams
    // localhost:8082/wavelenght/infos/echo?msg=rafx
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String GetEcho(  @RequestParam(value = "msg", defaultValue = nullmsg) String mess_to_echo){
        String out = "Null echo from GetEcho of the object: " + this.toString();
        if (mess_to_echo.equals(nullmsg)) {
            LOG.info(out);
            return out;
        }
        else{
            LOG.info("Message: "+mess_to_echo);
            return mess_to_echo;
        }
    }


    @Value("${server.port}")
    String port;

    private class ExtInfo
    {

        public String IPAddress;
        public String Port;
        public String ProcessID;
        public long ThreadID;

        public ExtInfo ()
        {
            IPAddress =  "0.0.0.0";
            Port = port;
            ProcessID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            ThreadID = currentThread().getId();

            try
            {
                IPAddress = InetAddress.getLocalHost().getHostAddress() ;
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
    }

    // localhost:8082/wavelenght/infos/ext-info
    @GetMapping(value = "/ext-info")
    public ExtInfo GetExtInfo (){
        ExtInfo extInfo = new ExtInfo();
        LOG.info(extInfo.toString());
        return extInfo;
    }


    //localhost:8082/wavelenght/infos/sqr/3
    @GetMapping(value = "/sqr/{nr:\\d+}")
    public Integer GetSquaredNumber(@PathVariable("nr") Integer nr) {
        LOG.info("Ive got: "+nr);
        return nr*nr;
    }


    //get header
    //localhost:8082/wavelenght/infos/login

    @GetMapping("/login")
    public String GetToken
        (@RequestHeader (value = "Authorization", defaultValue = "none")
                            String auth) {
        LOG.info("Received auth: "+auth);
        return "Authorization token: " + auth;
    }


    // localhost:8082/wavelenght/infos/resp-headers

    @GetMapping("/req-headers")
    String ShowRequestHeaders ( @RequestHeader MultiValueMap<String, String> headers){
        headers.forEach((key,value) ->{
            LOG.info(String.format("Header: %s = %s",key,value));
        });
        return "Number of request headers: "+headers.size();
    }

    // response header
    //localhost:8082/wavelenght/infos/resp-header
    @GetMapping("/resp-header")
    public ResponseEntity<String>  SetResponseHeader (){
        LOG.info("Set response header");
        return ResponseEntity.badRequest()
                .header("my-header", "my-header-value")
                .body("my-header was sent");
    }


    public class SomeResult  {
        private Integer fld1;
        private String fld2;

        public SomeResult() {
        }

        public SomeResult(Integer fld1, String fld2) {
            this();
            this.fld1 = fld1;
            this.fld2 = fld2;
        }

        public Integer getFld1() {
            return fld1;
        }

        public void setFld1(Integer fld1) {
            this.fld1 = fld1;
        }

        public String getFld2() {
            return fld2;
        }

        public void setFld2(String fld2) {
            this.fld2 = fld2;
        }
    }

    // localhost:8082/wavelenght/infos/custom-result
    @GetMapping("/custom-result")
    public ResponseEntity<SomeResult> GetCustomResponse(){
        SomeResult someResult = new SomeResult(2, "result002");
        //return ResponseEntity.status(HttpStatus.OK).body(someResult)
        return ResponseEntity
                .status(200)
                .header("SomeResult-id", "2")
                .body(someResult);
    } ;

}
