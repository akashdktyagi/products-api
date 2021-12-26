package com.yantraCloudApp.productsapi.repository;

import com.yantraCloudApp.productsapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Product,Integer> {
      Product findById(String productID);
      Product deleteById(String productID);
}
