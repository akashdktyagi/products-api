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
    String id;
    String title;
//    Integer userID;
    String name;
    String description;
    String price;
    String quantity;
    String ratings;
    String reviews;

//    isAddedToCart: false,
//    isAddedBtn: false,
//    isFavourite: false,
}


