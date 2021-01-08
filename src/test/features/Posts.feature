Feature: Send posts

  @test
  Scenario Outline: Make posts to server and check response code
    Given I add post with "<title>" "<body>"
    When I calls "addPostAPI" with "Post" http request
    Then the status code is 201
  Examples:
      |title        |body                                                |
      |do you hate  |I look for things, but rejected \ nal or to avoid it|
      |do you love  |I look for things, but rejected \ nal or to avoid it|
      |do you like  |I look for things, but rejected \ nal or to avoid it|

  @test
  Scenario: Make posts to server and check response contains title and author
    Given I have an end point posts
    When I post with title "new book" and author "my name"
    Then response includes the following
      | title  | new book |
      | author | my name  |