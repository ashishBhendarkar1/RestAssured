package RequestResponseSpecUsingBuilder;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
		         .setBaseUri("https://gorest.co.in")
		         .setContentType(ContentType.JSON)
		         .addHeader("Authorization", "Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		         .build();
		
		return reqSpec;
	}
	
	@Test
	public void getuser_withReqSpec() {
		given()
		      .spec(user_req_spec())
		       .when()
		        .get("/public/v2/users")
		         .then()
		          .statusCode(200);
	}
	
	@Test
	public void getuser_withReqSpec_queryparam() {
		given().log().all()
		      .queryParam("name", "ashish")
		      .spec(user_req_spec())
		       .when()
		        .get("/public/v2/users")
		         .then().log().all()
		          .statusCode(200);
	}

}
