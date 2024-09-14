package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept {
	
//	@BeforeMethod
//	public void setup() {
//		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
//	}
	
	
	@Test
	public void getContactsAPITest_WithInvalidToken() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		 .header("Authorization","Bearer -vOkuhSDY")
	      .when().log().all()
	       .get("/contacts")
	        .then().log().all()
	         .assertThat()
	          .statusCode(401)
	           .and()
	            .statusLine("HTTP/1.1 401 Unauthorized")
	             .and()
	              .body("error", equalTo("Please authenticate."));
	             
	}
	
	//extract() - fetch some value from JSON

	@Test
	public void getContactsAPITest_WithInvalidToken_WithExtract() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		String errormsg =given().log().all()
		 .header("Authorization","Bearer -vOkuhSDY")
	      .when().log().all()
	       .get("/contacts")
	        .then()
	         .extract()
	          .path("error");
		
		System.out.println(errormsg);
		Assert.assertEquals(errormsg, "Please authenticate.");
	       
	             
	}
	
	@Test
	public void getSingleUser_FetchUserData() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		  .get("/public/v2/users/7386816");
		
		System.out.println("status code: " +response.statusCode());
		System.out.println("status line: "+response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		int userID = js.getInt("id");
		System.out.println("user id: "+userID);
		
		String name = js.getString("name");
		System.out.println("name: "+name);
		
		String status = js.getString("status");
		System.out.println("status: "+status);
	}

	@Test
	public void getUser_FetchFullUserData() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
		 .header("Authorization","Bearer 4c54406d378d629fc322e99074263ab000cd8b2e984f9796b95e17cf13a6e66f")
		  .when()
		   .get("/public/v2/users");
		 
		System.out.println("status code: " +response.statusCode());
		System.out.println("status line: "+response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		List<Integer> ids = js.getList("id");
		System.out.println("user ids: "+ids);
    	//Assert.assertTrue(ids.contains("7386813"));
		
		for(Integer e : ids) {
			System.out.println(e);
		}
		
		List<String> names = js.getList("name");
		System.out.println("names: "+names);
	}
	
	@Test
	public void getProduct_FetchNestedData() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
				.when()
		         .get("/products");
		
		System.out.println("status code: " +response.statusCode());
		System.out.println("status line: "+response.statusLine());
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		List<Object> prices = js.getList("price");
		System.out.println(prices);
		
		List<Object> ratelist = js.getList("rating.rate");
		System.out.println(ratelist);
		
		List<Integer> countlist = js.getList("rating.count");
		System.out.println(countlist);
		
		List<Integer> ids = js.getList("id");
		System.out.println(ids);
		
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			Object price = prices.get(i);
			Object rate = ratelist.get(i);
			int count = countlist.get(i);
			
			System.out.println("Id is: "+ id + " price is:"+ price + " rate is: "+rate +" count is: "+ count );
		}
		
		Assert.assertTrue(ratelist.contains(4.7f));
	}
}
