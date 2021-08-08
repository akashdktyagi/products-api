package com.yantraCloudApp.productsapi.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Slf4j
@Data
public class Product {
    @Id
    String id;

    Integer userID;
    String name;
    String description;
    String expectedPrice;
    String quantity;
}
