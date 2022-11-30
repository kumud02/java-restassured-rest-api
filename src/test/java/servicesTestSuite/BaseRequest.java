package servicesTestSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseRequest {
    protected static RequestSpecification request;
    protected static Properties prop;

    @BeforeTest
    public void setEndPoint() throws FileNotFoundException, IOException {

        prop = new Properties();
        prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));

        RestAssured.baseURI = "https://" + prop.getProperty("server") + "/api/v1";
        request = RestAssured.given().log().all().contentType(ContentType.JSON);

        System.out.println(prop.getProperty("server"));
    }


    public String getAccessToken() {
        String requestBody = String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
                """, prop.getProperty("email"), prop.getProperty("password"));

                RestAssured.baseURI = "https://" + prop.getProperty("server") + "/api/v1";
                request = RestAssured.given().contentType(ContentType.JSON);


                Response response = request
                        .body(requestBody)
                        .post("/users/jwt/token/");

                JsonPath jsonPath = response.jsonPath();
                return jsonPath.getString("access");
    }

    public String getRefreshToken() {
        String requestBody = String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
                """, prop.getProperty("email"), prop.getProperty("password"));


        RestAssured.baseURI = "https://" + prop.getProperty("server") + "/api/v1";
        request = RestAssured.given().contentType(ContentType.JSON);


        Response response = request
                .body(requestBody)
                .post("/users/jwt/token/");

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("refresh");
    }


}
