package com.backend.demox.controllers;

import com.backend.demox.models.Product;
import com.backend.demox.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class) ;

    @Autowired
    private ProductService productService;

    @GetMapping(value="/products" )
    public List<Product> FindAll (){
        return  productService.FindAllProducts();
    }

    @GetMapping(value="/products/{id:\\d*}")
    public ResponseEntity<Product> FindOne(@PathVariable("id") long id){
        Product pro =  productService.FindOneProduct(id);
        final HttpStatus ret =  (pro  == null)? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return  ResponseEntity
                .status(ret)
                .body(pro);
    }

    @GetMapping(value="/clients/{id:\\d*}/products")
    public List<Product> FindAllOfClient(@PathVariable("id") long id)
    {
        return productService.FindProductsOfClient(id);
    }

    @GetMapping(value="/clients/{cli_id:\\d*}/products/{pro_id:\\d*}")
    public ResponseEntity<Product> FindOneOfClient(@PathVariable("cli_id") long clientId,
                                                   @PathVariable("pro_id") long productId)
    {
        Product pro =  productService.FindOneProduct(productId);
        if (pro == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        if (pro.getClient().getId() != clientId)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(pro);
    }
}



