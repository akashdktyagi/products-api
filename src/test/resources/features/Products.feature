Feature: As a User, I am able to create new product

  @create
  Scenario: I am able to create New Product
    Given I want to create a new product with all mandatory fields
    When I hit the api end point for create and with method as post
    Then a new product is successfully created

  @edit
  Scenario: I am able edit an existing Product
    Given I want to edit name field of an existing product
    When I hit the api end point for edit and with method as put
    Then the product is successfully edited

  Scenario: I am able to mock the DB
    Given I am able to mock the DB

