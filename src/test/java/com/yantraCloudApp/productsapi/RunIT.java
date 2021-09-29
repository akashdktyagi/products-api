package com.yantraCloudApp.productsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RunIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProductsRepository productsRepository;

	@Test
	void testInsertProductEndPoint() throws Exception {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		MvcResult mvcResult = mockMvc.perform(post("/insertProduct", 42L)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andReturn();

		Product serverResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Product.class);
		Product queryProductAgain = productsRepository.findById(serverResponse.getId());
		Assertions.assertThat(serverResponse).isNotNull();
		Assertions.assertThat(queryProductAgain).isNotNull();
		Assertions.assertThat(serverResponse).isEqualTo(queryProductAgain);
	}


}
