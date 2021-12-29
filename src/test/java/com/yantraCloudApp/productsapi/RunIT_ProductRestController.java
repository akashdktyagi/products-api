package com.yantraCloudApp.productsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import com.yantraCloudApp.productsapi.resource.ProductRestController;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class RunIT_ProductRestController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	ProductsRepository productsRepository;

	@BeforeEach
	public void setUp(){
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.insert(Mockito.any(Product.class))).then(AdditionalAnswers.returnsFirstArg());
		Mockito.when(productsRepository.findById(Mockito.any(String.class))).thenReturn(product);

	}

	@Test
	void createNewProduct() throws Exception {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		mockMvc.perform(post("/product", 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNotEmpty())
//				.andExpect(jsonPath("$.id").value(Matchers.instanceOf(UUID.class)))
				.andReturn();

	}

//	@Test
//	void updateExistingProduct() throws Exception{
//		MvcResult mvcResult = mockMvc.perform(get("/product",42L))
//				.andExpect(status().isOk())
//				.andReturn();
//		List listProducts = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$");
//		String idToModify = (String) ((LinkedHashMap)listProducts.get(0)).get("id");
//		Product product = Product.builder().withId(idToModify).withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
//		MvcResult mvcResultPut = mockMvc.perform(put("/product/"+idToModify, 42L)
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(product)))
//				.andExpect(status().isOk())
//				.andReturn();
//		System.out.println(mvcResultPut.getResponse().getContentAsString());
//
//	}

}
