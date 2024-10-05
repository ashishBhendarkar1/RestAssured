package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest {

	@Test
	public void getUserTest() {
		
		RequestSpecification requestspec = given().log().all()
		  .baseUri("https://gorest.co.in")
		  .header("Content-Type","application/json")
		  .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f");

		requestspec.when()
		            .get("/public/v2/users")
		             .then().log().all()
		              .assertThat()
		               .statusCode(200);
		
		requestspec.when()
		              .get("/public/v2/users/7450253")
		              .then().log().all()
		              .assertThat()
		               .statusCode(200);
	}
	
	@Test
	public void getUserTest_With_QueryParam() {
		RequestSpecification reqsepc = given().log().all()
		 .baseUri("https://gorest.co.in")
		 .header("Content-Type","application/json")
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		 .queryParam("name", "Aaditya");

		
		reqsepc.when()
		         .get("/public/v2/users")
		         .then().log().all()
	              .assertThat()
	               .statusCode(200);
	}
}
