package com.backend.wavelenght.controllers;


import com.backend.wavelenght.models.Element;
import com.backend.wavelenght.services.RemoteElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("remote-elements")
public class RemoteElementController {

    @Autowired
    private RemoteElementService remoteElementService ;

    @GetMapping("/elements")
    public List<Element> GetRemoteElements()
    {
        return remoteElementService.GetRemoteElements() ;
    }

    @GetMapping("/elements/{id}")
    public ResponseEntity<Element> GetRemoteOneElement(@PathVariable("id") Long id)
    {
        Optional<Element> oel = remoteElementService.GetRemoteOneElement(id) ;
        if (oel.isPresent()) {
            return  ResponseEntity.status(HttpStatus.OK).body(oel.get());
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/elements")
    public ResponseEntity<Element> CreateRemoteElement (@RequestBody Element el){


        return null ;
    }



}
