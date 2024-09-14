package BookingAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingAPIFeatureTests {
	
	String tokenId;
	
	@BeforeMethod
	public void getToken() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		tokenId =	given()
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
		
	}
	
	@Test
	public void getBookingIdsTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Response response = given().log().all()
		 .when().log().all()
		 .get("/booking")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .extract()
		 .response();
		
		JsonPath js = response.jsonPath();
		List<Integer> allIds = js.getList("bookingid");
		System.out.println("total booking ids:"+ allIds.size());
		
		// select count(*) from booking ---- for database check
		
		for (Integer id : allIds) {
			Assert.assertNotNull(id);
		}	
		
		}
	
	@Test
	public void getBookingIdTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//create a new booking id: POST call
		int newBookingId = createBooking();
		
		//get the same booking id: GET call
		given()
		 .pathParam("bookingID", newBookingId)
		 .when().log().all()
		 .get("/booking/{bookingID}")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200)
		 .and()
		 .body("firstname", equalTo("Jim"))
		 .and()
		 .body("bookingdates.checkin", equalTo("2018-01-01"));		
		}
	
	@Test
	public void createBookingTest() {
		Assert.assertNotNull(createBooking());
	}
	
	@Test
	public void updateBookingTest() {
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//POST call
		int newBookingId = createBooking();
		
		//get the same booking id: GET call
		verifyBookingId(newBookingId);
				
		//update the same booking id: PUT
				given()
				 .pathParam("bookingID", newBookingId)
				 .contentType(ContentType.JSON)
				 .header("Cookie","token="+tokenId)
				 .body("{\r\n" + 
				 		"    \"firstname\" : \"Ashish\",\r\n" + 
				 		"    \"lastname\" : \"Automation\",\r\n" + 
				 		"    \"totalprice\" : 111,\r\n" + 
				 		"    \"depositpaid\" : true,\r\n" + 
				 		"    \"bookingdates\" : {\r\n" + 
				 		"        \"checkin\" : \"2018-01-01\",\r\n" + 
				 		"        \"checkout\" : \"2019-01-01\"\r\n" + 
				 		"    },\r\n" + 
				 		"    \"additionalneeds\" : \"Dinner\"\r\n" + 
				 		"}")
				 .when().log().all()
				 .put("/booking/{bookingID}")
				 .then().log().all()
				 .assertThat()
				 .statusCode(200)
				 .and()
				 .body("firstname",equalTo("Ashish"))
				 .and()
				 .body("lastname",equalTo("Automation"))
				 .and()
				 .body("additionalneeds",equalTo("Dinner"));
				 
	}
	
	@Test
	public void partialBookingTest() {
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//POST call
		int newBookingId = createBooking();
		
		//get the same booking id: GET call
		verifyBookingId(newBookingId);
				
		//update the same booking id: PATCH
				given()
				 .pathParam("bookingID", newBookingId)
				 .contentType(ContentType.JSON)
				 .header("Cookie","token="+tokenId)
				 .body("{\r\n" + 
				 		"    \"firstname\" : \"API\",\r\n" + 
				 		"    \"lastname\" : \"Automation\"\r\n" + 
				 		"}")
				 .when().log().all()
				 .patch("/booking/{bookingID}")
				 .then().log().all()
				 .assertThat()
				 .statusCode(200)
				 .and()
				 .body("firstname",equalTo("API"))
				 .and()
				 .body("lastname",equalTo("Automation"));
				 
	}
	
	@Test
	public void deleteBookingTest() {
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//POST call
		int newBookingId = createBooking();
		
		//get the same booking id: GET call
		verifyBookingId(newBookingId);
				
		//update the same booking id: delete
				given()
				 .pathParam("bookingID", newBookingId)
				 .contentType(ContentType.JSON)
				 .header("Cookie","token="+tokenId)
				 .when().log().all()
				 .delete("/booking/{bookingID}")
				 .then().log().all()
				 .assertThat()
				 .statusCode(201);
				
		//get the same booking id is deleted or not: GET call
				given()
				 .pathParam("bookingID", newBookingId)
				 .when().log().all()
				 .get("/booking/{bookingID}")
				 .then().log().all()
				 .assertThat()
				 .statusCode(404);

				 
	}
	
	public void verifyBookingId(int newBookingId) {
		given()
		 .pathParam("bookingID", newBookingId)
		 .when().log().all()
		 .get("/booking/{bookingID}")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
	}
	
	public int createBooking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		int bookingId = given().log().all()
		 .contentType(ContentType.JSON)
		 .body("{\r\n" + 
		 		"    \"firstname\" : \"Jim\",\r\n" + 
		 		"    \"lastname\" : \"Brown\",\r\n" + 
		 		"    \"totalprice\" : 111,\r\n" + 
		 		"    \"depositpaid\" : true,\r\n" + 
		 		"    \"bookingdates\" : {\r\n" + 
		 		"        \"checkin\" : \"2018-01-01\",\r\n" + 
		 		"        \"checkout\" : \"2019-01-01\"\r\n" + 
		 		"    },\r\n" + 
		 		"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
		 		"}")
		 .when().log().all()
		 .post("/booking")
		 .then().log().all()
		 .extract()
		 .path("bookingid");
		
		System.out.println("booking id: " + bookingId);
		return bookingId;
		
	}

}
