package com.backend.demox.repositories;

import com.backend.demox.models.Client;
import com.backend.demox.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ProductRepository extends CrudRepository<Product, Long>
{

    // JPQL
    @Query(value = "SELECT pr FROM  Product pr WHERE pr.client  = ?1")
    List<Product> findByClient(Client cl);

    // SQL
    @Query(value = "SELECT * FROM  public.product pr WHERE pr.client_id  = ?1", nativeQuery = true)
    List<Product> findProductByClientID(Long cl_id);

}
