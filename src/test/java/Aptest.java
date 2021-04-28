import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Aptest {

  @Test
  public void firstApiTest() {
    Response response = given()
            .when()
            .get("https://projects.rsv-test.bizml.ru/api/v1/seasons/156")
            .then()
            .statusCode(200)
            .extract()
            .response();
    System.out.println(response.asString());
  }

  @Disabled
  @Test
  public void secondApiTest() {
   get("https://demoqa.com/")
            .then()
            .statusCode(201);
  }
  @Disabled
  @Test
  public void logApiTest() {
    given()
            .when()
            .get("https://projects.rsv-test.bizml.ru/api/v1/seasons/156")
            .then()
            .log().body()
            .statusCode(200)
            .extract()
            .response();

  }
  @Disabled
  @Test
  public void fieldApiTest() {
    given()
            .when()
            .get("https://projects.rsv-test.bizml.ru/api/v1/seasons/156")
            .then()
            .log().body()
            .statusCode(200)
            .body("data.project_id",is(123));

  }
  @Disabled
  @Test
  public void assertApiTest() {
    int result = given()
            .when()
            .get("https://projects.rsv-test.bizml.ru/api/v1/seasons/156")
            .then()
            .log().body()
            .statusCode(200)
            .extract().path("data.project_id");
    assertThat(result, is(122));
  }

  @Test
  public void objectApiTest() {
    ExtractableResponse<Response> result = given()
            .when()
            .get("https://projects.rsv-test.bizml.ru/api/v1/seasons/156")
            .then()
            .log().body()
            .statusCode(200)
            .extract();
    assertThat(result.path("data.project_id"), is(122));
  }
}
