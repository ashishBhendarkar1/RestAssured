package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

public class ProductAPIs {

	//BBD style test: given  --> when --> then
	// builder pattern
	@Test
	public void getProductsTest_1() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		   .get("/products")
		     .then()
		       .assertThat()
		         .statusCode(200);
		
	}
	
	@Test
	public void getProductsTest_2() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		  .when().log().all()
		    .get("/products")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200)
		        .and()
		         .body("$.size()", equalTo(30));
		
	}
}
