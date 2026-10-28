package comp.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() {
		Header authHeader = new Header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3OTMxODM0MTMsImV4cCI6MTc5MzE4NTIxM30.Ge-R9JE7-hojcFy9D-_bL2sCtPp3LvvWGJyKzBkDP0Q");
		given()
		.baseUri("https://dummyjson.com")
		.and()
		.header(authHeader)
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.get("/user")
		.then().log().all()
		.statusCode(200)
		.time(lessThan(3000L))
		.and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsRes.json"));
	}
}
