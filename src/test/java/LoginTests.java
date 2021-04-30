import api.Auth;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Text;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginTests extends Testbase {
  @Test
  void loginWithUiTest() {
    //authorize
    //pasha@test.ru1
    open("/login");
    $("#Email").val("pasha@test.ru");
    $("#Password").val("pasha@test.ru1");
    $(".button-1.login-button").click();
    //verify
    $(".account").shouldHave(text("pasha@test.ru"));
  }

  @Test
  void loginWithCookieTest() {
    Map<String, String> cookiesMap = new Auth().login("pasha@test.ru","pasha@test.ru1");
            given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", "pasha@test.ru")
                    .formParam("Password", "pasha@test.ru1")
                    .when()
                    .post("login")
                    .then()
                    .statusCode(302)
                    .log().body()
                    //.body("success", is(true))
                    .extract().cookies();
    //verify successful authorization
    open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/star-x-active.png");
    getWebDriver().manage().addCookie(new Cookie("Nop.customer",cookiesMap.get("Nop.customer")));
    getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH",cookiesMap.get("NOPCOMMERCE.AUTH")));
    getWebDriver().manage().addCookie(new Cookie("ARRAffinity",cookiesMap.get("ARRAffinity")));
    open("");
    $(".account").shouldHave(text("pasha@test.ru"));
  }
}
