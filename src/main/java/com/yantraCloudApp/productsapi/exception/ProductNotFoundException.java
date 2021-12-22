package com.yantraCloudApp.productsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String exception){
        super(exception);
    }
}
