package com.yantraCloudApp.productsapi.cucumber.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ScenarioScope
public class StepDefs {

    @Value("${token}")
    String token;

    String createEndPoint = "/product";
    String editEndPoint = "/product/%s";
    String deleteEndPoint = "/product/%s";
    String getEndPoint = "/product";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductsRepository productsRepository;

    Product product;
    MvcResult mvcResult;
    ResultActions resultActions;
    Scenario scenario;

    @Before // this is cucumber before not Junit before
    public void setUp(Scenario scenario){
        this.scenario = scenario;

    }

    @Given("I want to create a new product with all mandatory fields")
    public void iWanttoCreateNewProductWithAllMandatoryFields() {
        product = Product.builder()
                .withId("1")
                .withDescription("temp")
                .withName("tempName")
                .withPrice("12")
                .withQuantity("12")
                .build();
    }

    @Given("I want to edit name field of an existing product")
    public void iWantToEditNameFieldOfAnExistingProduct() throws Exception {
        iWanttoCreateNewProductWithAllMandatoryFields();
        iHitTheApiEndPointForCreateAndWithMethodAsPost();
    }

    @When("I hit the api end point for create and with method as post")
    public void iHitTheApiEndPointForCreateAndWithMethodAsPost() throws Exception {
        resultActions = mockMvc.perform(post(createEndPoint, 42L)
                .contentType("application/json")
                .header("Authorization","Bearer " +  token)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().is(200));
    }

    @When("I hit the api end point for edit and with method as put")
    public void iHitTheApiEndPointForEditAndWithMethodAsPut() throws Exception {
        Product editedProduct = Product.builder()
                .withId("1")
                .withDescription("NameChanged")
                .withName("tempName")
                .withPrice("12")
                .withQuantity("12")
                .build();

        resultActions = mockMvc.perform(put(String.format(editEndPoint,product.getId()), 42L)
                .header("Authorization","Bearer " +  token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(editedProduct)))
                .andExpect(status().is(200));
    }

    @Then("a new product is successfully created")
    public void aNewProductIsSuccessfullyCreated() throws Exception {
        mvcResult = resultActions.andReturn();
        Product serverResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Product.class);
        String jsonPretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serverResponse.toString());
        scenario.log("Server Response: " + jsonPretty );

        Product queryProductAgain = productsRepository.findById(serverResponse.getId());
        Assertions.assertThat(serverResponse).isNotNull();
        Assertions.assertThat(queryProductAgain).isNotNull();
        Assertions.assertThat(serverResponse.toString()).isEqualTo(queryProductAgain.toString());
    }

    @Then("the product is successfully edited")
    public void theProductIsSuccessfullyEditedWithStatusCodeAs201() throws Exception {

    }

    @Given("I am able to mock the DB")
    public void iAmAbleToMockTheDB(){

    }

}
