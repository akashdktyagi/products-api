package com.yantraCloudApp.productsapi;

import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import com.yantraCloudApp.productsapi.resource.ProductResource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

//@SpringBootTest==> Do not need this in these tests
@Slf4j
@ExtendWith(MockitoExtension.class)
class RunTest {

	ProductResource productResource;
	ProductsRepository productsRepository;

	@BeforeEach
	public void setUp(){
		productsRepository = Mockito.mock(ProductsRepository.class);
		this.productResource = new ProductResource(productsRepository);
	}
	@Test
	void testCreateProduct() {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.insert(any(Product.class))).then(returnsFirstArg());

		Product result = productResource.createProductMongo(product);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(product);
	}

	@Test
	void testFindAllProducts() {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.findAll()).thenReturn(Lists.newArrayList(product));

		List<Product> resultList = productResource.getProduct("3",1,2);
		Assertions.assertThat(resultList).isNotNull();
		Assertions.assertThat(resultList).isEqualTo(Lists.newArrayList(product));
	}

}
