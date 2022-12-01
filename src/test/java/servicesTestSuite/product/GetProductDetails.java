package servicesTestSuite.product;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

public class GetProductDetails extends BaseRequest {

    // https://test.api.testamplify.io/api/v1/products/detail/samsung-galaxy-s22-ultra-1/

    @Description("")
    @Test
    void validGetProductDetailsRequest() {
        String slug = "samsung-galaxy-s22-ultra-1";
        request
                .when()
                .get("/products/detail/"+slug)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Description("Verify message when invalid slug is submitted")
    @Test
    void invalidSlugIsPassed() {
        String slug = "invalid-slug";
        Response response = request
                .when()
                .get("/products/detail/"+slug);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),400, "Status code should be 400 but is "+response.statusCode());
        Assert.assertEquals(response.body().asString(), "\"Product Not found\"", "Validation message not displayed");
    }

}
