import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static utils.FileUtils.readStringFromFile;

public class CartTests extends Testbase {

  @Test
  void addInCart() {
    Response response =
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("Nop.customer=dc5265e2-4825-42b2-bbc8-84d0cf3b2001; ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46; ")
                    .body("product_attribute_16_5_4=14&product_attribute_16_6_5=16&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                    .when()
                    .post("/addproducttocart/details/16/1")
                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("success", is(true))
                    .extract().response();

    System.out.println(response);
  }
}

