package servicesTestSuite.product;

import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class GetProductCategory extends BaseRequest {

    // https://{{baseurl}}/api/v1/products/category/
    @Description("Verify Product Category")
    @Test
    void verifyValidProductCategoryFetch() {
        Response response = request
                .when()
                .header("Authorization", "Bearer "+getAccessToken())
                .get("/products/category/");

        // Print the response
        response.prettyPrint();

        // Parse Response to json
        JsonPath jsonPath = response.jsonPath();

        // Get the id list
        List<Integer> idList = jsonPath.getList("id");
        System.out.println(idList);

        // Verify id are unique
        ArrayList<Integer> distinctId = new ArrayList<>(new HashSet<>(idList));
        Assert.assertEquals(idList.size(), distinctId.size(), "Not distinct ID, Duplicate id detected");

        // Verify id are not null
        for(Integer id: idList) {
            Assert.assertNotNull(id, "id is null");

        /* Verify is active is true or false */
        List<Object> isActiveList = jsonPath.getList("is_active");
        for(Object isActive: isActiveList) {
            Assert.assertTrue(isActive.getClass().getSimpleName().contentEquals("Boolean"),"Not boolean type, it has different data type");
        }
        }




    }

    @Description("Authentication is not passed")
    @Test
    void incorrectAuthenticationIsPassed() {
//        request
//                .when()
//                .header("Authorization", "Bearer "+ "invalidToken")
//                .get("/products/category/")
//                .then()
//                .assertThat()
//                .statusCode(401);

        Response response =  request
                .when()
                .header("Authorization", "Bearer"+ "invalidToken")
                .get("/products/category/");

        response.prettyPrint();

    }
}
