package servicesTestSuite.product;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

import java.util.HashMap;
import java.util.Map;

public class GetProductList extends BaseRequest {
    // https://test.api.testamplify.io/api/v1/products/list/?category_id=12&name=&page=1&page_size=10

    @Test
    void validGetProductListRequest() {
        // Passing Single Parameter
        request
                .when()
                .param("category_id","12")
                .param("name","")
                .param("page","1")
                .param("page_size","10")
                .get("/products/list/")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    void submitWithInvalidCategoryID() {

        // Request Parameters - Passing Multiple Parameters
        Map<String, String> requestParameters = new HashMap<String, String>();
        requestParameters.put("category_id", "1");
        requestParameters.put("name", "");
        requestParameters.put("page","1");
        requestParameters.put("page_size","10");
        request
                .when()
                .params(requestParameters)
                .get("/products/list/")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
