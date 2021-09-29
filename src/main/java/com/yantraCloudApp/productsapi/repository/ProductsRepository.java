package com.yantraCloudApp.productsapi.repository;

import com.yantraCloudApp.productsapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Product,Integer> {
//
//    public Product findByFirstName(String firstName);
//    public List<Product> findByLastName(String lastName);

      public Product findById(String productID);

}
