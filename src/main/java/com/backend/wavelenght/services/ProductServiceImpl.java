package com.backend.wavelenght.services;

import com.backend.wavelenght.models.Product;
import com.backend.wavelenght.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {



    @Autowired
    private ProductRepository productRepository ;

    @Override
    @PreAuthorize("hasRole('manager2')")
    public Product FindOneProduct(Long productId) {
        Optional<Product> pro =  productRepository.findById(productId);
        if (pro.isPresent ())
            return pro.get();
        else
            return null;
    }

    @Override
    public Product FindOneProductOfClient(Long productId, Long clientId) {
        return null;
    }

    @Override
    public List<Product> FindAllProducts() {
        return (List<Product>) productRepository.findAll() ;
    }

    @Override
    public List<Product> FindProductsOfClient(Long clientId) {
        return productRepository.findProductByClientID(clientId) ;

    }
}
