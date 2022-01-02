package com.yantraCloudApp.productsapi.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
//@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest
public class CucumberSpringConfiguration { }