package GETAPITestsWithBDD;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


import java.util.Map;

public class GETAPIWithQueryParamsAndPathParams {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
	}

	@Test
	public void getUserWith_QueryParams() {
			
		given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		 .queryParam("name", "trivedi")
		 .queryParam("status", "active")
		  .when().log().all()
		   .get("/public/v2/users")
		    .then().log().all()
		     .assertThat()
		      .statusCode(200)
		       .and()
		        .contentType(ContentType.JSON);
		 
	}
	
	@Test
	public void getUserWith_QueryParams_WithHashMap() {
	
//		Map<String , String> queryMap = new HashMap<String, String>();
//		queryMap.put("name", "trivedi");
//		queryMap.put("status", "active");
//		queryMap.put("gender", "male");

	// Map.of() is immutable (can't change map values)	
		Map<String , String> queryMap =Map.of(
				"name", "trivedi",
				"status", "active"
				); 
		
		given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		 .queryParams(queryMap)
		  .when().log().all()
		   .get("/public/v2/users")
		    .then().log().all()
		     .assertThat()
		      .statusCode(200)
		       .and()
		        .contentType(ContentType.JSON);
		 
	}
	
  // PATH parameter
	
	@DataProvider
	public Object [] [] getUserData() {
		return new Object [] [] {
	//		{"6940718"},
	//		{"6940719"},
			{"7381532"}
		};
	}
	
	
	@Test(dataProvider = "getUserData")
	public void getUserAPI_WithPathParams(String userid) {
		
		given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		  .pathParam("userid", userid)
		   .when().log().all()
		    .get("/public/v2/users/{userid}/posts")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200)
		        .and()
		         .body("title", hasItem("Comitatus reiciendis congregatio tandem temeritas certo crebro.")); 
		  
	}
}
