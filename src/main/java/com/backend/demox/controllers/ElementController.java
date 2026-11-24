package com.backend.demox.controllers;

import com.backend.demox.models.Element;
import com.backend.demox.services.ElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ElementController {

    private static Logger LOG = LoggerFactory.getLogger(ElementController.class);

    @Autowired
    private ElementService elementService;

    @GetMapping("/elements/ping")
    public String PingElementController()
    {
        String mess = "ElementController is working";
        LOG.info(mess);
        return mess;
    }

    //localhost:8082/demox/elements
    @GetMapping("/elements")
    public List<Element> GetElements(){
        return elementService.GetAllElements();
    }

    // localhost:8082/demox/elements/1
    @GetMapping("/elements/{id}")
    public ResponseEntity<Element> GetOneElements( @PathVariable("id") Long eid){
        Element el = elementService.GetOneElement(eid);
        if (el==null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        else
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(el);
    }

        // localhost:8082/demox/elements
    @PostMapping("/elements")
    public ResponseEntity<Element> CreateElement(@RequestBody Element el){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(elementService.CreateElement(el));
    }


    //headers = {"Content-type=application/json"}
    // localhost:8082/demox/elements/1
    @PutMapping ("/elements/{id}")
    public ResponseEntity<Element> ModifyElement
            ( @PathVariable("id") Long id,
              @RequestBody Element el){

        el.setIdEl(id);
        Element el_result = elementService.ModifyElement(el);
        if (el_result == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        else
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(el_result);
    }
}
