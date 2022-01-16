package com.yantraCloudApp.productsapi.resource;

import com.yantraCloudApp.productsapi.exception.ProductNotFoundException;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

// There is another way of using Mockito.
// By extending MockitoExtension in junit we can use Mockito @Mock and @InjectMocks annotation
//https://reflectoring.io/unit-testing-spring-boot/
@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest_anotherWay {

//    OAuth2User principal;

    @InjectMocks
    ProductRestController productRestController;


    @Mock
    ProductsRepository productsRepository;

// No need to do this since now using MockitoExtension and can use @Mock and @Inject annotation to inject
//    @BeforeEach
//    public void setUp(){
//        productsRepository = Mockito.mock(ProductsRepository.class);
//        productRestController = new ProductRestController(productsRepository);
//    }

    @Test
    void createProductMongo() {
        Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
        Mockito.when(productsRepository.insert(any(Product.class))).then(returnsFirstArg());

        Product result = productRestController.createProductMongo(product);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(product);
    }

}