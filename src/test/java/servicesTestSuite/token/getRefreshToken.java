package servicesTestSuite.token;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

import static org.hamcrest.Matchers.*;


public class getRefreshToken extends BaseRequest {

    @Description("Valid Request is passed")
    @Test
    void validRequestIsSubmitted() {
        String requestBody = String.format("""
                {
                  "refresh": "%s"
                }
                """, getRefreshToken());

        request
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users/jwt/refresh/")
                .then()
                .assertThat()
                .statusCode(200)
                .body("access", notNullValue());

    }

    @Description("Invalid Access Token is Passed")
    @Test
    void invalidRefreshTokenIsPassed() {
        String requestBody = String.format("""
                {
                  "refresh": "%s"
                }
                """, "invalidAccessToken");

        request
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users/jwt/refresh/")
                .then()
                .assertThat()
                .statusCode(401)
                .body("detail", equalTo("Token is invalid or expired"))
                .body("code",equalTo("token_not_valid"));

    }

}
