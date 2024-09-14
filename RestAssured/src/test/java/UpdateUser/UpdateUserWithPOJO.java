package UpdateUser;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserWithPOJO {
	
	//1. create a user : POST
	//2. GET
	//3. Update a user : PUT
	
	public String getRandomEmailId() {
		return "apiuser"+System.currentTimeMillis()+"@apis.com";
	}
	
	@Test
	public void updateUserWith_POJO() {
		RestAssured.baseURI ="https://gorest.co.in";
		User user = new User("ashish", getRandomEmailId(), "male", "active");
		
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
		
		//2. update : PUT : update a user
		user.setName("Ashish Bhendarkar");
		user.setStatus("inactive");
		given().log().all()
				 .contentType(ContentType.JSON)
				  .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
				   .body(user)
				    .when().log().all()
				     .put("/public/v2/users/" +userId)
				      .then().log().all()
				       .assertThat()
				        .statusCode(200)
				         .and()
				          .body("name", equalTo(user.getName()))
				           .and()
				            .body("id", equalTo(userId))
				             .and()
				              .body("status", equalTo(user.getStatus()));
	}

}
