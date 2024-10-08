package RequestResponseSpecUsingBuilder;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class RequestResponseSpecTest {
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
		         .setBaseUri("https://gorest.co.in")
		         .setContentType(ContentType.JSON)
		         .addHeader("Authorization", "Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		         .build();
		
		return reqSpec;
	}
	
	public static ResponseSpecification get_res_spec_200OK() {
		ResponseSpecification resspec = new ResponseSpecBuilder()
		         .expectContentType(ContentType.JSON)
		         .expectStatusCode(200)
		         .expectHeader("Server", "cloudflare")
		         .build();
		
		return resspec;
	}
	

	@Test
	public void getUserTest() {
		given()
		 .spec(user_req_spec())
		  .when()
		   .get("/public/v2/users")
		    .then()
		     .assertThat()
		     .spec(get_res_spec_200OK());
	}
}
