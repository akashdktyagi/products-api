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
  @ExtendWith(MockitoExtension.class)
  class RunTest {

  ProductResource productResource;
  ProductsRepository productsRepository;
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