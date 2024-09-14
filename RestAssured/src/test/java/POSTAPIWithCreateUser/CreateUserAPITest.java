package POSTAPIWithCreateUser;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	@Test
	public void getAuthTokenTest_WithJSONFile()
	{
		RestAssured.baseURI = "https://gorest.co.in/";
		
		Integer userId = given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		 .contentType(ContentType.JSON)
		 .body(new File("./src/test/resources/jsons/user.json"))
		 .when().log().all()
		 .post("/public/v2/users")
		 .then().log().all()
		 .assertThat()
		 .statusCode(201)
		 .extract()
		 .path("id");
		
		System.out.println("user id:" + userId);
		Assert.assertNotNull(userId);
	}

}
