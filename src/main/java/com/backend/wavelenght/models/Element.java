package com.backend.wavelenght.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Element", namespace = "http://rafx.show.org.pl/ws", propOrder = {
        "idEl",
        "nameEl",
        "sizeEl"
})
public class Element {
    @XmlElement(name = "IdEl")
    private Long idEl;
    @XmlElement(name = "NameEl")
    private String nameEl;
    @XmlElement(name = "SizeEl")
    private Integer sizeEl;

    public Element() {
    }

    public Element(Long idEl, String nameEl, Integer sizeEl) {
        this.idEl = idEl;
        this.nameEl = nameEl;
        this.sizeEl = sizeEl;
    }

    public Long getIdEl() {
        return idEl;
    }

    public void setIdEl(Long idEl) {
        this.idEl = idEl;
    }

    public String getNameEl() {
        return nameEl;
    }

    public void setNameEl(String nameEl) {
        this.nameEl = nameEl;
    }

    public Integer getSizeEl() {
        return sizeEl;
    }

    public void setSizeEl(Integer sizeEl) {
        this.sizeEl = sizeEl;
    }
}
