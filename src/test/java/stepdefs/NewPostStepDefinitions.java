package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class NewPostStepDefinitions extends Utils {

    ResponseSpecification resspec;
    Response response;
    RequestSpecification res;
    TestDataBuilder data = new TestDataBuilder();

    @Given("I add post with {string} {string}")
    public void iAddPostWith(String title, String body) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPost(title, body));
    }

    @When("I calls {string} with {string} http request")
    public void iCallsWithPostHttpRequest(String resource,String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resspec = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int arg0) {
        assertEquals(response.getStatusCode(), 201);
    }

}