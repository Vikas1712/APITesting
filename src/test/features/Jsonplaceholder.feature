Feature: Validating Jsonplaceholder API

  @PostsAPI
  @test
  Scenario Outline: Make posts to server and check response code and title
    Given I add post with "<title>" "<body>"
    When I calls "addPostAPI" with "Post" http request
    Then the status code is 201
    And validate "title" is equal "<title>" in the response
    Examples:
      |title |body                              |
      |TDD   |Test Driven Development           |
      |BDD   |Behavior Driven Development       |
      |ATDD  |Acceptance Test Driven Development|

  @UsersAPI
  @test
  Scenario: Call users end point and check response code
    Given I have a service to request list of users
    When I calls "getUsersAPI" with "Get" http request
    And I request list of users
    Then the status code is 200

  @UsersAPI
  @test
  Scenario: Call users end point and check first user
    Given I have a service to request list of users
    When I calls "getFirstUsersAPI" with "Get" http request
    Then the status code is 200
    And "username" in response body is "Bret"
    And "name" in response body is "Leanne Graham"
    And "id" in response body is "1"

  @UsersAPI
  @test
  Scenario: Search for users with username Delphine
    Given I have a service to request users with name "Delphine"
    When I calls "getUsersAPI" with "Get" http request
    Then the status code is 200
    And "username" in response body is "Delphine"
    And "name" in response body is "Glenna Reichert"
    And "id" in response body is "9"
