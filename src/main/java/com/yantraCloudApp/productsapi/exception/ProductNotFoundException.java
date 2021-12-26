package com.yantraCloudApp.productsapi.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String exception){
        super(exception);
    }
}
