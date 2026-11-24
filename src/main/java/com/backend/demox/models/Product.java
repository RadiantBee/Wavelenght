package com.backend.demox.models;


import jakarta.persistence.*;

@Table(name="product")
@Entity
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Product_name", length = 127)
    private String productName;

    @ManyToOne(fetch=FetchType.EAGER)
    //@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Product() {}

    public Product(String productName) {
        this();
        this.productName = productName;
    }
    public Product(Long id, String productName) {
        this(productName) ;
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }
}

