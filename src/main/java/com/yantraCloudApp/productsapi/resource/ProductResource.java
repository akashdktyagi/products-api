package com.yantraCloudApp.productsapi.resource;

import com.yantraCloudApp.productsapi.exception.ProductNotFoundException;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/")
@Log4j2
public class ProductResource {

    private ProductsRepository productsRepository;

    public ProductResource(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }
    @PostMapping("/product")
    public Product createProductMongo(@RequestBody Product product){
        log.debug("Create product: "+ product);
        product.setId(generateUUID());
        return productsRepository.insert(product);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable String id){
        log.debug("Delete product with id: "+ id);

        Optional<Product> product = Optional.ofNullable(productsRepository.findById(id));
        if (product.isEmpty()){
            throw new ProductNotFoundException("Product can not be found with Id: " + id + " Can not delete.");
        }
        return productsRepository.deleteById(id);

    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product productToBeUpdated, @PathVariable String id)    {
        log.debug("Update product with details: "+ productToBeUpdated);
        Optional<Product> productOriginal = Optional.ofNullable(productsRepository.findById(id));
        if (productOriginal.isEmpty()){
            throw new ProductNotFoundException("Product can not be found with Id: " + id + " Can not update.");
        }
        productToBeUpdated.setId(id);

        return productsRepository.save(productToBeUpdated);
    }

    @GetMapping("/product")
    public List<Product> getProduct(
            @Parameter(in = ParameterIn.QUERY, description = "pass an optional search string for looking up product with Product Name as" , schema=@Schema())
            @Valid
            @RequestParam(value = "withProductNameAs", required = false)
            String name,
            @Min(0)@Parameter(in = ParameterIn.QUERY, description = "number of records to skip for pagination" ,schema=@Schema(allowableValues={  }))
            @Valid
            @RequestParam(value = "skip", required = false) Integer skip,
            @Min(0) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "maximum number of records to return" ,schema=@Schema(allowableValues={  }, maximum="50"))
            @Valid @RequestParam(value = "limit", required = false) Integer limit
    ){
        List<Product> productList =  productsRepository.findAll();
        log.debug(productList);
        return productList;
    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
