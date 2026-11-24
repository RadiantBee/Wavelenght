package com.backend.demox.services;

import com.backend.demox.models.Product;

import java.util.List;


public interface ProductService {

    Product FindOneProduct (Long productId);
    Product FindOneProductOfClient (Long productId, Long clientId);
    List<Product> FindAllProducts();
    List<Product> FindProductsOfClient (Long clientId);
}
