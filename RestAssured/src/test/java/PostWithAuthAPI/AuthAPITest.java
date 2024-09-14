package PostWithAuthAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

import static io.restassured.RestAssured.given;

import java.io.File;

import javax.security.auth.login.CredentialException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthAPITest {
	
	@Test
	public void getAuthTokenTest_WithJSON() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
	String tokenId =	given()
		 .contentType(ContentType.JSON)
		 .body("{\r\n" + 
		 		"    \"username\" : \"admin\",\r\n" + 
		 		"    \"password\" : \"password123\"\r\n" + 
		 		"}")
		  .when().log().all()
		  .post("/auth")
		  .then()
		  .assertThat()
		  .statusCode(200)
		  .extract()
		  .path("token");
	
	System.out.println("token id ==>"+tokenId);
	Assert.assertNotNull(tokenId);
	}

	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
	String tokenId =	given()
		 .contentType(ContentType.JSON)
		 .body(new File("./src/test/resources/jsons/auth.json"))
		  .when().log().all()
		  .post("/auth")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .extract()
		  .path("token");
	
	System.out.println("token id ==>"+tokenId);
	Assert.assertNotNull(tokenId);
	}
	
//pojo --> json -serialization
//json --> pojo -deserialization
//jackson-databind lib	
	@Test
	public void getAuthTokenTest_WithPOJOClass() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials cred = new Credentials("admin","password123");
		
	String tokenId =	given()
		 .contentType(ContentType.JSON)
		 .body(cred) //pojo to json: serialization: ObjectMapper(Jackson)
		  .when().log().all()
		  .post("/auth")
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .extract()
		  .path("token");
	
	System.out.println("token id ==>"+tokenId);
	Assert.assertNotNull(tokenId);
	}
	
}
