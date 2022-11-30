package servicesTestSuite.Misc;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import servicesTestSuite.BaseRequest;

public class OptionRequest extends BaseRequest {

    @Description("Testing Option Request")
    @Test
    void testOptionRequest() {
       Response response =  request
                                .when()
               .header("Authorization", "Bearer "+getAccessToken())
                                .options("/users/jwt/token/");
       response.prettyPrint();
       response.statusCode();
        System.out.println(response.getHeaders());
        System.out.println("Server is: "+ response.header("Server"));

    }

}
