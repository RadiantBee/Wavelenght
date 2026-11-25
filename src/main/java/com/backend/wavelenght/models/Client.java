package com.backend.wavelenght.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="sdname",length = 127)
    private String sdname;

    @JsonIgnore()
    @OneToMany(mappedBy="client",fetch=FetchType.LAZY)
    // @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Product> products;

    public Client() {
    }

    public Client(Long id, String name, String sdname) {
        this.id = id;
        this.name = name;
        this.sdname = sdname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdname() {
        return sdname;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sdname='" + sdname + '\'' +
                '}';
    }
}
