@tag
Feature: Purchase the order from Ecommerce site
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce site

  @Regression
  Scenario Outline: Positive test on submitting the order
    Given I want to login with username <email> and password <password>
    When Add product <productName> to the cart
    When checkout with <productName> and submit the order
    Then verify "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | email                         |password         |productName          |
      | raju.saranyaa18@gmail.com     |DancingDoll@18   |ZARA COAT 3          |
