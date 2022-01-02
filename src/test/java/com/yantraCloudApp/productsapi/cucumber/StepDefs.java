package com.yantraCloudApp.productsapi.cucumber;

import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@CucumberContextConfiguration
public class StepDefs  {

    @Autowired
    ProductsRepository productsRepository;

    @Given("I have product to be created with all mandatory fields")
    public void iHaveProductToBeCreatedWithAllTheMandatoryFields() {
        Assertions.assertThat(productsRepository).isNotNull();
    }

    @When("I hit the api end point as {string} and with method as {string}")
    public void iHitTheApiEndPointAsWithMethodAs(String endPoint, String method) {

    }

    @Then("a new product is successfully created")
    public void aNewProductIsSuccessfullyCreated() {

    }

}
