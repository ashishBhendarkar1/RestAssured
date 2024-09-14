package GorestAPITests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GorestAPIFeatureTests {
	
	String tokenid = "Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f";
	@BeforeMethod
	public void baseURI() {
		RestAssured.baseURI = "https://gorest.co.in";
	}
	
	@Test
	public void getUserIdsTest() {
		
		Response response =given()
		 .header("Authorization", tokenid)
		  .when().log().all()
		   .get("/public/v2/users")
		    .then().log().all()
		     .assertThat()
		      .statusCode(200)
		       .and()
		        .extract()
		         .response();
		
		response.prettyPrint();
		JsonPath js = response.jsonPath();
		List<Integer> ids = js.getList("id");
		System.out.println("total user id:"+ids.size());
		
		for (Integer id : ids) {
			Assert.assertNotNull(id);
		}
		
	}
	
	@Test
	public void getUserIdTest() {
		
		//create a new user id : POST call
		int userID = createUserId();
		
		//get same userid : GET call
		     given()
				 .header("Authorization", tokenid)
				  .pathParam("userid",userID)
				  .when().log().all()
				   .get("/public/v2/users/{userid}")
				    .then().log().all()
				     .assertThat()
				      .statusCode(200)
				       .and()
				        .body("name", equalTo("API"));
		
		
	}
	
	@Test
	public void createaUserTest() {
		Assert.assertNotNull(createUserId());
	}
	
	@Test
	public void updateUserTest() {
		//Post call
		int userID = createUserId();
		
		//GET call
		verifyUserID(userID);
		
		//PUT call
		given().log().all()
		 .pathParam("UserID", userID)
		  .header("Authorization",tokenid)
		   .contentType(ContentType.JSON)
		    .body("{\r\n" + 
		 		" \"name\":\"Ashish\",\r\n" + 
		 		" \"email\":\"" + randomEmailId() +  "\",\r\n" + 
		 		" \"gender\":\"male\",\r\n" + 
		 		" \"status\":\"active\"\r\n" + 
		 		"}\r\n" + 
		 		"")
		     .when().log().all()
		      .put("/public/v2/users/{UserID}")
		       .then().log().all()
		        .assertThat()
		         .statusCode(200)
		          .and()
		           .body("name", equalTo("Ashish"));
	}
	
	@Test
	public void partialUserTest() {
		//Post call
				int userID = createUserId();
				
				//GET call
				verifyUserID(userID);
				
				//PATCH call
				given().log().all()
				 .pathParam("UserID", userID)
				  .header("Authorization",tokenid)
				   .contentType(ContentType.JSON)
				    .body("{\r\n" + 
				 		" \"name\":\"Jim\",\r\n" + 
				 		" \"status\":\"inactive\"\r\n" + 
				 		"}\r\n" + 
				 		"")
				     .when().log().all()
				      .patch("/public/v2/users/{UserID}")
				       .then().log().all()
				        .assertThat()
				         .statusCode(200)
				          .and()
				           .body("name", equalTo("Jim"))
				            .and()
				             .body("status", equalTo("inactive"));
		
	}
	
	@Test
	public void deleteaUserTest() {
		       //Post call
				int userID = createUserId();
				
				//GET call
				verifyUserID(userID);
				
				//Delete call
				given().log().all()
				.pathParam("UserID", userID)
				 .header("Authorization",tokenid)
				  .when().log().all()
				   .delete("/public/v2/users/{UserID}")
				    .then().log().all()
				     .assertThat()
				      .statusCode(204);
				
				//Get call
				given()
				 .header("Authorization", tokenid)
				  .pathParam("userid",userID)
				  .when().log().all()
				   .get("/public/v2/users/{userid}")
				    .then().log().all()
				     .assertThat()
				      .statusCode(404)
				       .and()
				        .body("message", equalTo("Resource not found"));
		
	}
	
	public void verifyUserID(int userID) {
		given()
		 .header("Authorization", tokenid)
		  .pathParam("userid",userID)
		  .when().log().all()
		   .get("/public/v2/users/{userid}");
	}
	
	public String randomEmailId()
	{
		return "api"+System.currentTimeMillis()+"@gmail.com";
	}
	
	public int createUserId()
	{
		Integer userId = given().log().all()
				 .header("Authorization",tokenid)
				  .contentType(ContentType.JSON)
				   .body("{\r\n" + 
				 		" \"name\":\"API\",\r\n" + 
				 		" \"email\":\"" + randomEmailId() +  "\",\r\n" + 
				 		" \"gender\":\"male\",\r\n" + 
				 		" \"status\":\"active\"\r\n" + 
				 		"}\r\n" + 
				 		"")
				     .when().log().all()
				      .post("/public/v2/users")
				       .then().log().all()
				        .assertThat()
				         .statusCode(201)
				          .extract()
				           .path("id");
				
				System.out.println("user id:" + userId);
				return userId;
	}
}
