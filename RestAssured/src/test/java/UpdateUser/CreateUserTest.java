package UpdateUser;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import UpdateUser.UserLombok;

public class CreateUserTest {
	public String getRandomEmailId() {
		return "apiuser"+System.currentTimeMillis()+"@apis.com";
	}
	
	@Test
	public void updateUserWith_Lombok() {
		RestAssured.baseURI ="https://gorest.co.in";
		UserLombok user = new UserLombok("ashish", getRandomEmailId(), "male", "active");
		
		//1. post: create a user
		Response postResponse = given()
		 .contentType(ContentType.JSON)
		  .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		   .body(user)
		    .when()
		     .post("/public/v2/users");
		postResponse.prettyPrint();
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("userid ==>"+userId);
		
}
	
	@Test
	public void updateUserWith_Lombok_Builder() {
		RestAssured.baseURI ="https://gorest.co.in";
		
		//creating user class object using lambok
		//builder pattern
		UserLombok userLombok = new UserLombok.UserLombokBuilder()
				                    .name("ashish")
				                    .email(getRandomEmailId())
				                    .gender("male")
				                    .status("active")
				                    .build();
		
		//1. post: create a user
		Response postResponse = given()
		 .contentType(ContentType.JSON)
		  .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		   .body(userLombok)
		    .when()
		     .post("/public/v2/users");
		postResponse.prettyPrint();
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("userid ==>"+userId);
}
}