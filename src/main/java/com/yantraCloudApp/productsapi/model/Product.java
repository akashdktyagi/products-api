package com.yantraCloudApp.productsapi.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Slf4j
@Builder(setterPrefix = "with")
@Getter @Setter
@ToString
public class Product {
    @Id @NotNull
    String id;
    String title;
    String name;
    String description;
    String price;
    String quantity;
    String ratings;
    String reviews;

}


