package com.yantraCloudApp.productsapi.resource;

import com.yantraCloudApp.productsapi.mapper.ProductMapper;
import com.yantraCloudApp.productsapi.model.Product;
import com.yantraCloudApp.productsapi.repository.ProductsRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
//@RequestMapping("/rest/product")
@RequestMapping("/")
@Log4j2
public class ProductResource {

//    @Autowired
////    ProductMapper productMapper;

    @Autowired
    private ProductsRepository productsRepository;

    @PostMapping("/insertProduct")
    public Product createProductMongo(@RequestBody Product product){
        Product p = productsRepository.insert(product);
        return p;
    }

//    @PostMapping
//    public Product createProduct(@RequestBody Product product){
//        log.debug(product.toString());
//        productMapper.insertProduct(product);
//        log.debug("Product Inserted: " + product.toString());
//        return product;
//    }

//    @GetMapping("/name")
//    public List<Product> getProduct(
//            @Parameter(in = ParameterIn.QUERY, description = "pass an optional search string for looking up product with Product Name as" , schema=@Schema())
//            @Valid
//            @RequestParam(value = "withProductNameAs", required = false)
//            String withProductNameAs,
//            @Min(0)@Parameter(in = ParameterIn.QUERY, description = "number of records to skip for pagination" ,schema=@Schema(allowableValues={  }))
//            @Valid
//            @RequestParam(value = "skip", required = false) Integer skip,
//            @Min(0) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "maximum number of records to return" ,schema=@Schema(allowableValues={  }, maximum="50"))
//            @Valid @RequestParam(value = "limit", required = false) Integer limit
//    ){
//        List<Product> products=null;
//        if (withProductNameAs==null){
//            products = productMapper.getProducts();
//        }else{
//            products = productMapper.getProductWithProductNameAs(withProductNameAs);
//        }
//        return null;
//    }


}
