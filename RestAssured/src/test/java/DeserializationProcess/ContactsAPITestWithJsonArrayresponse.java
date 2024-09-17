package DeserializationProcess;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITestWithJsonArrayresponse {
	@Test
	public void createContactsTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		
	//1. get the all contact id using: GET
		Response getresponse = given().log().all()
		 .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk4ZmUyMWU0ZDJjZjAwMTM0YzA4NzIiLCJpYXQiOjE3MjY1OTA1MTB9.Ko2WDtz89wU5OzbU0vUJ5RJwazgvWsFIozoW2zrKZIc")
		  .when().log().all()
		   .get("/contacts");
		
		getresponse.prettyPrint();
		System.out.println("======================");
		
//wip		
	//De-serialization: JSON to POJO
		ObjectMapper mapper =new ObjectMapper();
		try {
			ContactsUser[] contactres = mapper.readValue(getresponse.body().asString(),ContactsUser[].class);
		
			List<String> objids;

			
			for (ContactsUser contUser : contactres) {
				System.out.println("contact user id: "+contUser.get_id());
				System.out.println("contact user first name: "+contUser.getFirstName());
				
				System.out.println("=======================");
				
			//					objids..add(contUser.get_id());
			//	System.out.println("contactres object list:"+objids);
				
				
		//	Assert.assertNotNull(contUser.get_id());
			}
			
			//System.out.println("contactres object list:"+objids);
			
		//collect all the ids in Array list and verify all ids are not null	
			
			List<String> ids = getresponse.jsonPath().get("_id");
			for (String s : ids) {
				System.out.println("contact id is :"+s);
				Assert.assertNotNull(s);
			}
			
			
			} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
	}

}
