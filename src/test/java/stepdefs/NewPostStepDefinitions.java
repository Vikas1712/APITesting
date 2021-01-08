package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuilder;
import resources.Utils;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class NewPostStepDefinitions extends Utils {

    ResponseSpecification resspec;
    Response response;
    RequestSpecification res;
    TestDataBuilder data=new TestDataBuilder();

    @Given("I have an end point posts")
    public void iHaveAnEndPointPosts() {
        res=given().spec(requestSpecification()).body(data.addPost());
        resspec =new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
    }

    @When("I post with title {string} and body {string}")
    public void iPostWithTitleAndBody(String title, String body) {
        response =res.when().post("/posts").
                then().spec(resspec).extract().response();
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int arg0) {
        assertEquals(response.getStatusCode(),201);
    }
}
