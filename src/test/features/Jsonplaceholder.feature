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

  @test
  Scenario: Call users end point and check first user
    Given I have a service to request list of users
    When I calls "getFirstUsersAPI" with "Get" http request
    Then the status code is 200
    And response includes username "Bret" and name "Leanne Graham" fields

  @test
  Scenario: Search for users with username Delphine