@tag
Feature: validate application login error
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: incorrect login username or password
    Given I landed on Ecommerce site
    When I want to login with username <email> and password <password>
    Then verify error "Incorrect email or password." message is displayed

 Examples: 
      | email                         |password         |
      | raju.saranyaa@gmail.com       |DancingDoll@18   |
