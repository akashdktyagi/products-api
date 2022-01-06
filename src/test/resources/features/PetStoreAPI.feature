@issue:GCRM-30975 # User story Jira Ticket
Feature: I want to test pet store api
  # "default" is the key under urls in config.yml
  Background: I invoke a browser
    Given with api base url as "default"
    And with swagger schema validation path as "swagger.json"
    And with headers key as "header1"

  @xrayTest:GCRM-31472 # Test Case Xray Jira Number
  Scenario: Get request
    When with request end point as "/v2/pet/findByStatus?status=available"
    And with method as "get"
    Then status as 200

#    Lots of Validation steps are already defined and directly can be used
#    However, you can also write your own validation steps

#    Then response contains string as "{string}"
#    Then response does not contains string as "{string}"
#    Then response contains string mentioned in below list
#    Then response does not contains string mentioned in below list
#    Then response json path as "{string}" has value which contains "{string}"
#    Then response json path as "{string}" has value which does not contains "{string}"
#    Then response json path as "{string}" has value equal to "{string}"
#    Then response json path and values mentioned in below table
#    Then response json path "{string}" is not null
#    Then response json path is not null in below list
#    Then response field represented as json path in below list is present in response
#    Then response field represented as xml path in below list is present in response
#    Then response field represented as json path in below list is not present in response
#    Then response field represented as xml path in below list is not present in response
#    Then response of xml path is not empty in below list
#    Then response xml path "sdfs" is not null
#    Then response of xml path is not empty in below list
#    Then response xml path is not null in below list
#    Then response xml path "{string}" is not null
#    Then response xml path as "{string}" has value equal to "{string}"
