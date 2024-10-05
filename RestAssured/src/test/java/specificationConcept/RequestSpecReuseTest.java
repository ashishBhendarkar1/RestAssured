package specificationConcept;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

public class RequestSpecReuseTest {
	RequestSpecification requestspec;
	
	@BeforeTest
	public void setup() {
	 requestspec = given().log().all()
			  .baseUri("https://gorest.co.in")
			  .header("Content-Type","application/json")
			  .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f");
	}
	
	@Test
	public void getUserTest() {
		requestspec.when()
		            .get("/public/v2/users")
		             .then()
		              .statusCode(200);
	}
	
	@Test
	public void getAUserTest() {
		requestspec.when()
		            .get("/public/v2/users/7374316")
		             .then().log().all()
		              .statusCode(200);
	}
	
	@Test
	public void getUserTest_With_Query() {
		requestspec.when()
		           .queryParam("name", "Baalaaditya")
		            .get("/public/v2/users")
		             .then().log().all()
		              .statusCode(200);
	}
	
	@Test
	public void getWrongUserTest() {
		requestspec.when()
		            .get("/public/v2/users/007")
		             .then()
		              .statusCode(404);
	}


}
