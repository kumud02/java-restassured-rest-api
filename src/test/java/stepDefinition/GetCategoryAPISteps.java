package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.notNullValue;

public class GetCategoryAPISteps {
    private RequestSpecification requestSpecification;
    private Response response;
    @Given("^End Point is given$")
    public void setAPIEndPoint() {
        RestAssured.baseURI ="https://test.api.testamplify.io/api/v1";
        requestSpecification = RestAssured.given();
    }

    @And("^Get Category Request is sent$")
    public void sendGetCategoryRequest() {
        response = requestSpecification.get("/products/category/");
        System.out.println(response.prettyPrint());
    }

    @Then("^Status Code should be 200$")
    public void verifyStatusCodeIs200() {
        response.then().statusCode(200);
    }

    @And("body should not be null")
    public void countValueShouldNotBeNull() {
        response.then().body("$.",notNullValue());
    }
}
