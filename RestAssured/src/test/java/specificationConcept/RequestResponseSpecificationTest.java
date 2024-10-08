package specificationConcept;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RequestResponseSpecificationTest {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	@BeforeTest
	public void stepup(){
		reqSpec = given().log().all()
				      .baseUri("https://jsonplaceholder.typicode.com")
				      .header("Content-Type", "application/json");
		
		resSpec = expect()
				   .statusCode(anyOf(equalTo(200), equalTo(201)))
				   .header("Content-Type", "application/json; charset=utf-8")
                   .header("Server", "cloudflare")
                   .time(lessThan(6000L));
				   
	}
	
	@Test
	public void checkGetTest() {
		reqSpec
		      .when()
		       .get("/posts/1")
		        .then()
		         .spec(resSpec)
		         .body("userId", equalTo(1))
		         .body("id", equalTo(1));
	}
	
	@Test
	public void checkPostTest() {
		reqSpec
		    .body("{\r\n" + 
		    		"    \"title\":\"foo\",\r\n" + 
		    		"    \"body\":\"bar\",\r\n" + 
		    		"    \"userId\":1\r\n" + 
		    		"}")
		      .when()
		       .post("/posts")
		        .then()
		         .spec(resSpec)
		         .body("userId", equalTo(1))
		         .body("id", equalTo(101))
		         .body("title", equalTo("foo"));
	}
	
	@Test
	public void checkGet_WithQueryParamTest() {
		reqSpec
		      .queryParam("userId", 1)
		      .when()
		       .get("/posts/1")
		        .then()
		         .spec(resSpec)
		         .body("userId", equalTo(1))
		         .body("id", equalTo(1));
	}
}
