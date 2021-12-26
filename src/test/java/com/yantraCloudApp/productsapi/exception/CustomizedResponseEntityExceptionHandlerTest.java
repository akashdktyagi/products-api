package com.yantraCloudApp.productsapi.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomizedResponseEntityExceptionHandlerTest {

    CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler = new CustomizedResponseEntityExceptionHandler();
    ProductNotFoundException productNotFoundException = new ProductNotFoundException("Product Not Found.");

    @Mock
    WebRequest webRequest;

    @Test
    void handleProductNotFound() {
        Mockito.when(webRequest.getDescription(false)).thenReturn("web request uri location");
        ResponseEntity<ErrorDetails> responseEntity = customizedResponseEntityExceptionHandler.handleProductNotFound(productNotFoundException,webRequest);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(responseEntity.getBody().getDetails()).isEqualTo("web request uri location");
        Assertions.assertThat(responseEntity.getBody().getMessage()).isEqualTo("Product Not Found.");
        Assertions.assertThat(responseEntity.getBody().getTimestamp()).isInstanceOf(Date.class);

    }

    @Test
    void handleProductAnyException() {
        Mockito.when(webRequest.getDescription(false)).thenReturn("web request uri location");
        ResponseEntity<ErrorDetails> responseEntity = customizedResponseEntityExceptionHandler.handleProductAnyException(new Exception("Any exception"),webRequest);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(responseEntity.getBody().getDetails()).isEqualTo("web request uri location");
        Assertions.assertThat(responseEntity.getBody().getMessage()).isEqualTo("Any exception Error while processing your request");
        Assertions.assertThat(responseEntity.getBody().getTimestamp()).isInstanceOf(Date.class);

    }
}