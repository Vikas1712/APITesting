package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public Utils() {
    }

    public RequestSpecification requestSpecification() throws IOException {
        if (req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fileInputStream=new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp= response.asString();
        JsonPath jsonPath=new JsonPath(resp);
        return jsonPath.get(key).toString();
    }
}
