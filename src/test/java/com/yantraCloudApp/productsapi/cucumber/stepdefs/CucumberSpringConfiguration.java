package com.yantraCloudApp.productsapi.cucumber.stepdefs;

import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
//@ContextConfiguration(classes = {Scenario.class})
@SpringBootTest
@AutoConfigureMockMvc
public class CucumberSpringConfiguration { }