Feature: As a User, I am able to create new product

  @create
  Scenario: I am able to create New Product
    Given I have product to be created with all mandatory fields
    When I hit the api end point as "/product" and with method as "post"
    Then a new product is successfully created
