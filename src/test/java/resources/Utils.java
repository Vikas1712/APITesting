package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.baseURI;

public class Utils {
    RequestSpecification req;

    public RequestSpecification requestSpecification(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        req =new RequestSpecBuilder().setBaseUri(baseURI)
                .setContentType(ContentType.JSON).build();
        return req;
    }
}
