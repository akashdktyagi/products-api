package com.yantraCloudApp.productsapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Slf4j
@Data
@Builder(setterPrefix = "with")
public class Product {
    @Id
    String productID;

    Integer userID;
    String name;
    String description;
    String expectedPrice;
    String quantity;
}
