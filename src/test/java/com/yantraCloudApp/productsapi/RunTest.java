package com.yantraCloudApp.productsapi;

import com.yantraCloudApp.productsapi.exception.ProductNotFoundException;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
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
		productResource = new ProductResource(productsRepository);
	}

	@Test
	void testCreateProduct() {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.insert(any(Product.class))).then(returnsFirstArg());

		Product result = productResource.createProductMongo(product);
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(product);
	}

	@Test
	void testFindAllProducts() {
		Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.findAll()).thenReturn(Lists.newArrayList(product));

		List<Product> resultList = productResource.getProduct("3",1,2);
		assertThat(resultList).isNotNull();
		assertThat(resultList).isEqualTo(Lists.newArrayList(product));
	}

	@Test
	void testUpdateProduct(){
		Product productSentForUpdate = Product.builder().withId("1234").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();

		//Set mock for findById and save
		Mockito.when(productsRepository.findById("1234")).thenReturn(productSentForUpdate);
		Mockito.when(productsRepository.save(productSentForUpdate)).thenReturn(productSentForUpdate);

		Product updatedProductReturn = productResource.updateProduct(productSentForUpdate,"1234");
		assertThat(updatedProductReturn).isEqualTo(productSentForUpdate);

	}

	@Test
	void testUpdateProductNoProductFound(){
		Product product = Product.builder().withId("1234").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
		Mockito.when(productsRepository.findById("1234")).thenReturn(null);

		//when
		Throwable thrown = catchThrowable(() -> {
			productResource.updateProduct(product,"1234");
		});

		// then
		assertThat(thrown)
				.isInstanceOf(ProductNotFoundException.class)
				.hasMessageContaining("Product can not be found with Id: 1234 Can not update");

	}

	@Test
	void testDeleteProduct(){
		String id = "1234";
		Product product = Product.builder().withId("1234").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();

		//Set mock for findById and delete
		Mockito.when(productsRepository.findById("1234")).thenReturn(product);
		Mockito.when(productsRepository.deleteById("1234")).thenReturn(product);
		Product productDeleted = productResource.deleteProduct(id);

		Assertions.assertThat(product).isEqualTo(productDeleted);
	}

	@Test
	void deleteProductExceptionProductNotFound(){
		Mockito.when(productsRepository.findById("1234")).thenReturn(null);

		//when
		Throwable thrown = catchThrowable(() -> {
			productResource.deleteProduct("1234");
		});

		// then
		assertThat(thrown)
				.isInstanceOf(ProductNotFoundException.class)
				.hasMessageContaining("Product can not be found with Id: 1234 Can not delete");

	}
}
