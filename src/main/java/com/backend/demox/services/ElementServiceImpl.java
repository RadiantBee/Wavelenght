package com.backend.demox.services;

import com.backend.demox.models.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

    private static Logger LOG = LoggerFactory.getLogger(ElementServiceImpl.class);

    private  List<Element> li;

    public ElementServiceImpl() {
        li = new ArrayList<Element>();
        Element el1 = new Element(1L, "name01", 11);
        Element el2 = new Element(2L, "name02", 22);
        li.add(el1);
        li.add(el2);
    }

    @Override
    public List<Element> GetAllElements() {
        return li;
    }

    @Override
    public Element GetOneElement(Long id) {
        Element el = null;
        for (Element ee: li){
            if (ee.getIdEl()==id){
                el = ee;
                break;
            }
        }
        return el;
    }

    @Override
    public  Element CreateElement(Element el_to_create) {

        //Comparator<Element> comparator = Comparator.comparing( Element::getIdEl );
        Comparator<Element> comparator = Comparator.comparing( element -> element.getIdEl() );
        Object lock = new Object();

        Long nextId = 0L;
        synchronized (lock) {
            Element ElMax = li.stream().max(comparator).get();
            if (ElMax == null)
                nextId = 1L;
            else
                nextId = ElMax.getIdEl() + 1;

            el_to_create.setIdEl(nextId);
            li.add(el_to_create);
        }
        return el_to_create;
    }

    @Override
    public Element ModifyElement(Element el_to_modify) {

        Element el_found = li.stream()
                .filter(el -> el.getIdEl()== el_to_modify.getIdEl())
                .findAny()
                .orElse(null);
        if (el_found == null)
            return  null;
        else
        {
            el_found.setNameEl(el_to_modify.getNameEl());
            el_found.setSizeEl(el_to_modify.getSizeEl());
            return  el_found;
        }
    }
}
