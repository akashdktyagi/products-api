package com.yantraCloudApp.productsapi.testContainers;

import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class RunIT_MongoDBTest {

    @Autowired
    ProductsRepository productsRepository;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @AfterEach
    void cleanUp() {
        this.productsRepository.deleteAll();
    }

    @Test
    public void testInsertMethod(){
        Product product = Product.builder()
                .withId("1")
                .withPrice("12")
                .withDescription("descp")
                .withName("My Product")
                .build();
        productsRepository.insert(product);

        Product product1 = productsRepository.findById("1");

        Assertions.assertThat(product1.getId()).isEqualTo(product.getId());
        Assertions.assertThat(product1.getName()).isEqualTo(product.getName());

    }

}
