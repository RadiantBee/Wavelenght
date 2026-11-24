package com.backend.demox.services;

import com.backend.demox.models.Element;

import java.util.List;

public interface ElementService {
    List<Element> GetAllElements();
    Element GetOneElement(Long id);
    Element CreateElement(Element el_to_create);
    Element ModifyElement (Element el_to_modify);
}
