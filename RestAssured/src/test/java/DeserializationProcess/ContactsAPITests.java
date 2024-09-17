package DeserializationProcess;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ContactsAPITests {
	
	@Test
	public void createContactsTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
	//1. create a user contact : POST 	
		ContactsUser contact = new ContactsUser.ContactsUserBuilder()
		      .firstName("abc")
		      .lastName("xyz")
		      .birthdate("2024-01-01")
		      .email("abc@gmail.com")
		      .phone(99999999)
		      .street1("nagar road")
		      .street2("na")
		      .city("pune")
		      .stateProvince("ms")
		      .postalCode(123456)
		      .country("india")
		      .build();
		
		Response response = given().log().all()
		  .contentType(ContentType.JSON)
		   .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk4ZmUyMWU0ZDJjZjAwMTM0YzA4NzIiLCJpYXQiOjE3MjY1OTA1MTB9.Ko2WDtz89wU5OzbU0vUJ5RJwazgvWsFIozoW2zrKZIc")
		    .body(contact) // auto serialized to JSON from POJO 
		     .when().log().all()
		      .post("/contacts");
		
		response.prettyPrint();
		String contactid = response.jsonPath().get("_id");
		System.out.println("contact id is :"+contactid);
		System.out.println("======================");
		
	//2. get the same contact id: GET
		Response getresponse = given().log().all()
		 .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk4ZmUyMWU0ZDJjZjAwMTM0YzA4NzIiLCJpYXQiOjE3MjY1OTA1MTB9.Ko2WDtz89wU5OzbU0vUJ5RJwazgvWsFIozoW2zrKZIc")
		  .when().log().all()
		   .get("/contacts/"+contactid);
		
		getresponse.prettyPrint();
		System.out.println("======================");
		
		
	//De-serialization: JSON to POJO
		ObjectMapper mapper =new ObjectMapper();
		try {
			ContactsUser contactres = mapper.readValue(getresponse.body().asString(),ContactsUser.class);
		
		System.out.println(contactres.get_id() + " " + contactres.getFirstName());
		Assert.assertEquals(contactres.get_id(), contactid);
		Assert.assertEquals(contactres.getFirstName(), contact.getFirstName());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
	}

}
