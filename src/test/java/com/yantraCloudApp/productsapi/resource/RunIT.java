package com.yantraCloudApp.productsapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(ProductRestController.class)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
class RunIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	ProductsRepository productsRepository;

	@Test
	void createNewProduct() throws Exception {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		MvcResult mvcResult = mockMvc.perform(post("/product", 42L)
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


}
