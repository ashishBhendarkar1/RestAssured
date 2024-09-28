package Auth;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthAPIsTest {
	
	@Test
	public void basicAuthTest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		given()
		  .auth()
		    .basic("admin", "admin")
		      .when()
		        .get("/basic_auth")
		         .then().log().all()
		           .assertThat()
		             .statusCode(200);
		          
	}
	
	@Test
	public void preemptiveAuthTest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		given()
		  .auth()
		   .preemptive()
		    .basic("admin", "admin")
		      .when()
		        .get("/basic_auth")
		         .then().log().all()
		           .assertThat()
		             .statusCode(200);
		          
	}
	
	@Test
	public void digestiveAuthTest() {
		RestAssured.baseURI = "https://postman-echo.com";
		given()
		  .auth()
		    .digest("postman", "password")
//		     .header("realm","Users")
//		     .header("nonce","nevEKlhTmNsPkla2UNMs4FHwEJdmQOqr")
//		     .header("qop","auth")
		      .when()
		        .get("/digest-auth")
		         .then().log().all()
		           .assertThat()
		             .statusCode(200)
		              .and()
		                .body("authenticated", equalTo(true));
		          
	}

}
