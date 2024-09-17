package DeserializationProcess;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPITest {

	@Test
	public void createUserWith_Lombok()
	{
		RestAssured.baseURI = "https://gorest.co.in";
	//create a user : POST	
		User user = new User.UserBuilder()
		       .name("ashish")
		       .email("ashish23@gmail.com")
		       .gender("male")
		       .status("active")
		       .build();
		
		
		Response response = given().log().all()
		  .contentType(ContentType.JSON)
		   .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		    .body(user) // this is serialization : pojo/lombok to json
		     .when().log().all()
		      .post("/public/v2/users");
		
		response.prettyPrint();
		Integer userid = response.jsonPath().get("id");
		System.out.println("user id is: "+userid);
		
		System.out.println("===================");
		
	// get same user id created : GET	
		Response getresponse = given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		  .when().log().all()
		   .get("/public/v2/users/" + userid);
		getresponse.prettyPrint();
		
	}
	
}
