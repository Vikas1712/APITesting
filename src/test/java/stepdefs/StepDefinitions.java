package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuilder data = new TestDataBuilder();
    String id=null;
    @Given("I add post with {string} {string}")
    public void iAddPostWith(String title, String body) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPost(title, body));
    }

    @When("I calls {string} with {string} http request")
    public void iCallsWithPostHttpRequest(String resource,String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response =res.when().get(resourceAPI.getResource());
        resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int code) {
        assertEquals(response.getStatusCode(), code);
    }

    @And("validate {string} is equal {string} in the response")
    public void validateIsEqualInTheResponse(String keyValue, String ExpectedValue) {
        assertEquals(getJsonPath(response,keyValue),ExpectedValue);
    }

    @Given("I have a service to request list of users")
    public void iHaveAServiceToRequestListOfUsers() throws IOException {
        res = given().spec(requestSpecification());
    }

    @When("I request list of users")
    public void iRequestListOfUsers() {
        String resp= response.asString();
        JsonPath jsonPath=new JsonPath(resp);
        int count = jsonPath.getInt("array.size()");
        assertEquals(count,10);
    }

    @Given("I have a service to request users with name {string}")
    public void iHaveAServiceToRequestUsersWithName(String name) throws IOException {
        res = given().spec(requestSpecification().queryParam("username",name));
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @When("I Search for the post written by the user")
    public void iSearchForThePostWrittenByTheUser() throws IOException {
        id=getJsonPath(response,"id");
        res = given().spec(requestSpecification().queryParam("userId",id));
    }

    @When("I fetch the comments made by user for each posts")
    public void iFetchTheCommentsMadeByUserForEachPosts() throws IOException {
        res = given().spec(requestSpecification().queryParam("postId",id));
    }

    @And("Validate {string} address format in comments sections")
    public void validateAddressFormatInCommentsSections(String email) {
        List<String> emailAddress= Collections.singletonList(getJsonPath(response, email));
        /* Regex to restrict leading, trailing, or consecutive dots in emails */
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);

        for(String em : emailAddress){
            Matcher matcher = pattern.matcher(em);
            System.out.println(em +" : "+ matcher.matches());
        }
    }
}
