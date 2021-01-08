Feature: Send posts

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