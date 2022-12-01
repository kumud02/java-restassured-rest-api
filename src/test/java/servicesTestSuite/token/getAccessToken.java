package servicesTestSuite.token;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class getAccessToken extends BaseRequest {

    @Description("Submit Valid Get Token Request")
    @Test
    void verifyValidGetTokenRequest(){
        String requestBody = "{\n" +
                "                    \"email\": \""+prop.getProperty("email")+"\",\n" +
                "                    \"password\": \""+prop.getProperty("password") +"\"\n" +
                "                }";

        request
                .body(requestBody)
                .post("/users/jwt/token/")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("access", not(empty()))
                .body("access",notNullValue())
                .body("refresh", not(empty()))
                .body("refresh", notNullValue());
    }

    @Description("Verify Response when no credential is passed")
    @Test
    void testNoCredentialsIsPassed() {

        request
                .contentType(ContentType.JSON)
                .post("/users/jwt/token/")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("email[0]", equalTo("This field is required."))
                .body("password[0]", equalTo("This field is required."));

    }


    @Description("Verify Response when invalid credential is passed")
    @Test
    void testWhenPassedInvalidCredentials() {
        String requestBody = "{\n" +
                "                    \"email\": \""+prop.getProperty("email")+"\",\n" +
                "                    \"password\": \""+prop.getProperty("password") +"\"\n" +
                "                }";


        request
                .body(requestBody)
                .post("/users/jwt/token/")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("non_field_errors[0]",equalTo("Unable to log in with provided credentials."));

    }

    @Description("Validate for invalid methods")
    @Test
    void validateWhenInvalidMethodIsPassed() {
        String requestBody = "{\n" +
                "                    \"email\": \""+prop.getProperty("email")+"\",\n" +
                "                    \"password\": \""+prop.getProperty("password") +"\"\n" +
                "                }";

        request
                .body(requestBody)
                .get("/users/jwt/token/")
                .then()
                .assertThat()
                .statusCode(405)
                .body("detail",equalTo("Method \"GET\" not allowed."));
    }
}
