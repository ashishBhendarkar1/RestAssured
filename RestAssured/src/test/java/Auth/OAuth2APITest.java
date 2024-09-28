package Auth;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OAuth2APITest {
	private String accesstoken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		Response response = given()
		 .contentType(ContentType.URLENC)
		  .formParam("grant_type", "client_credentials")
		  .formParam("client_id", "IbAS2FzVg9xlhoy9A4KqIP7cpGVTo5vb")
		  .formParam("client_secret", "Mi1Er288RaxJFHIs")
		   .when()
		    .post("/v1/security/oauth2/token");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		accesstoken = response.jsonPath().getString("access_token");
	}
	
	@Test
	public void getFlightDetailsTest_1() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		Response response = given()
		 .header("Authorization", "Bearer "+accesstoken)
		  .queryParam("origin", "PAR")
		   .queryParam("maxPrice", 200)
		    .when()
		     .get("/v1/shopping/flight-destinations");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
	//	System.out.println(response.getBody().asString());
		      
	}

	@Test
	public void getFlightDetailsTest_2() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		Response response = given()
		 .auth()
		  .oauth2(accesstoken)
		  .queryParam("origin", "PAR")
		   .queryParam("maxPrice", 200)
		    .when()
		     .get("/v1/shopping/flight-destinations");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
	//	System.out.println(response.getBody().asString());
		      
	}

}
