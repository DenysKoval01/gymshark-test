Feature: Add To Bag

  Scenario: Adding a product to the Bag
    Given the user is on a product page
    When adding the product to the Bag
    Then the product should appear in the Bag


  Scenario: Remove product from the bag
    Given there are products in the bag
      | Arrival T-Shirt|
      | Crest Hoodie |
    When I remove a product "Crest Hoodie"
    Then the product is removed from the bag "Crest Hoodie"

  Scenario: Add quantity to bag
    Given there are products in the bag
      | Arrival T-Shirt|
      | Crest Hoodie   |
    When I choose quantity 10 to "Arrival T-Shirt"
    Then product quantity is equal to value "10" in product "Arrival T-Shirt"

  Scenario: Remove quantity from bag
    Given there are products in the bag
      | Arrival T-Shirt|
      | Crest Hoodie   |
    When I choose quantity 10 to "Arrival T-Shirt"
    And I choose quantity 8 to "Arrival T-Shirt"
    Then product quantity is equal to value "8" in product "Arrival T-Shirt"