package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ContactsAPIsTests {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}
	
	@Test
	public void getContactsAPITest() {
		
		//RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk4ZmUyMWU0ZDJjZjAwMTM0YzA4NzIiLCJpYXQiOjE3MjU0NjQzNDJ9.yJ5E0-vOkuhSDb3ofVicVap1RExLJZEjxX1uG5uWfOY")
	      .when().log().all()
	       .get("/contacts")
	        .then().log().all()
	         .assertThat()
	          .statusCode(200)
	           .and()
	            .statusLine("HTTP/1.1 200 OK")
	             .and()
	              .contentType(ContentType.JSON)
	               .and()
	                .body("$.size()", equalTo(17));
	}

	@Test
	public void getContactsAPITest_WithInvalidToken() {
		
		//RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization","Bearer -vOkuhSDY")
	      .when().log().all()
	       .get("/contacts")
	        .then().log().all()
	         .assertThat()
	          .statusCode(401)
	           .and()
	            .statusLine("HTTP/1.1 401 Unauthorized");
	             
	}

}
