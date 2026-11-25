package com.backend.wavelenght.clients;

import com.backend.wavelenght.models.Element;

import java.util.List;
import java.util.Optional;

public interface ElementClient {

    List<Element> GetElements ();

    Optional<Element> GetOneElement(Long id);

    Optional<Element> CreateElement (Element el) ;
}
