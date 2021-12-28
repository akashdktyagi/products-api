# products-api
Products to sell

## Swagger UI: 
* http://localhost:9095/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## Notes:
* Unit Test does not need Spring Context. That's why they are cheap and fast.
* Integration test are costly but gives overall behaviour.
* Code Coverage can also be calculated at Integration test level.
* Mockito Extension is to be used with Junit 5 Extension API in Unit Tests
   ```java


      // There is another way of using Mockito.
      // By extending MockitoExtension in junit we can use Mockito @Mock and @InjectMocks annotation
      //https://reflectoring.io/unit-testing-spring-boot/
      @ExtendWith(MockitoExtension.class)
      class ProductRestControllerTest_anotherWay {

    @InjectMocks
    ProductRestController productRestController;

    @Mock
    ProductsRepository productsRepository;

    // No need to do this since now using MockitoExtension and can use @Mock and @Inject annotation to inject
    //    @BeforeEach
    //    public void setUp(){
    //        productsRepository = Mockito.mock(ProductsRepository.class);
    //        productRestController = new ProductRestController(productsRepository);
    //    }

    @Test
    void createProductMongo() {
        Product product = Product.builder().withId("1").withDescription("temp").withName("tempName").withPrice("12").withQuantity("12").build();
        Mockito.when(productsRepository.insert(any(Product.class))).then(returnsFirstArg());

        Product result = productRestController.createProductMongo(product);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(product);
    }

}
    ```
* SpringExtension.class is to be used with Junit 5 Extension API in Integration Test
    ```java
    @SpringBootTest
    @Slf4j
    @ExtendWith(SpringExtension.class)
    @AutoConfigureMockMvc
    class RunIT {
    
        @Autowired
        MockMvc mockMvc;
    
        @Autowired
        ObjectMapper objectMapper;
    ```
* Note: There is no Spring Boot Context in Unit Test. @SpringBootTest annotation brings spring boot context and spring boot capabilities like auto-wiring etc.
