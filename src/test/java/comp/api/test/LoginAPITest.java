package comp.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() {
		UserCredentials user = new UserCredentials("emilys", "emilyspass");

		given()
		.baseUri("https://dummyjson.com/user")
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(user)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.post("/login")
		.then().log().all()
		.statusCode(200)
		.time(lessThan(3000L))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
