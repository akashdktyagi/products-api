package com.yantraCloudApp.productsapi.testContainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RunIT_WithTestContainer {

	@Value("${token}")
	String token;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProductsRepository productsRepository;

	@Test
	void createNewProduct() throws Exception {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();

		MvcResult mvcResult = mockMvc.perform(post("/product", 42L)
				.header("Authorization","Bearer " +  token)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andReturn();

		Product serverResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Product.class);
		Product queryProductAgain = productsRepository.findById(serverResponse.getId());
		Assertions.assertThat(serverResponse).isNotNull();
		Assertions.assertThat(queryProductAgain).isNotNull();
		Assertions.assertThat(serverResponse.toString()).isEqualTo(queryProductAgain.toString());
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
