package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class ResponseSpecificationTest {
	
	@Test
	public void resSpecTest() {
		ResponseSpecification resSpec = expect()
				                          .statusCode(200)
				                          .header("Content-Type", "application/json; charset=utf-8")
				                          .body("userId",equalTo(1));
		
		given()
		     .baseUri("https://jsonplaceholder.typicode.com")
		      .when()
		       .get("/posts/1")
		        .then()
		         .spec(resSpec);
	}

}
