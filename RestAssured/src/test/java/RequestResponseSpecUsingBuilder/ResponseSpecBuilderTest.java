package RequestResponseSpecUsingBuilder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification get_res_spec_200OK() {
		ResponseSpecification resspec = new ResponseSpecBuilder()
		         .expectContentType(ContentType.JSON)
		         .expectStatusCode(200)
		         .expectHeader("Server", "cloudflare")
		         .build();
		
		return resspec;
	}
	
	public static ResponseSpecification get_res_spec_401_AuthFail() {
		ResponseSpecification resspec = new ResponseSpecBuilder()
		         .expectStatusCode(401)
		         .expectHeader("Server", "cloudflare")
		         .build();
		
		return resspec;
	}

	@Test
	public void getUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
		  .header("Authorization", "Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		   .when()
		    .get("/public/v2/users")
		     .then()
		      .spec(get_res_spec_200OK());
	}
	
	@Test
	public void getUser_WithInvalidTokenTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
		  .header("Authorization", "Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66fff")
		   .when()
		    .get("/public/v2/users")
		     .then()
		      .spec(get_res_spec_401_AuthFail());
	}
}
