package com.backend.wavelenght.clients;

import com.backend.wavelenght.models.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class ElementClientImpl implements ElementClient{

    @Value("${element.url}")
    private String element_url;  // elem-dest.topic

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Element> GetElements() {
        String url = element_url + "/elements" ;
        return restTemplate.getForObject(url, List.class );
    }

    @Override
    public Optional<Element> GetOneElement(Long id) {
        String url = element_url + "/elements" +"/" +id.toString();
        ResponseEntity<Element> response =  restTemplate.getForEntity(url, Element.class );
        if (response.getStatusCode()== HttpStatus.OK){
            return Optional.of (response.getBody());
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Element> CreateElement(Element el) {
        String url = element_url + "/elements";
        HttpEntity<Element> request = new HttpEntity<>(el);
        return Optional.of(restTemplate.postForObject(url,request,Element.class));
    }
}
