package GETAPITestsWithoutBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ContactsAPIsTest {

     @Test
     public void getProductsTest1() {
    	 RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
    	 RequestSpecification request = RestAssured.given();
    	 
    	 request.header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk4ZmUyMWU0ZDJjZjAwMTM0YzA4NzIiLCJpYXQiOjE3MjU0NjQzNDJ9.yJ5E0-vOkuhSDb3ofVicVap1RExLJZEjxX1uG5uWfOY");
    	 
    	 Response response = request.get("/contacts");
    	 
    	 int statuscode = response.statusCode();
    	 System.out.println("status code is :"+statuscode);
    	 
    	 String statusline = response.statusLine();
    	 System.out.println("status line is :"+statusline);
    	 
    	 Assert.assertEquals(statuscode, 200);
    	 Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
    	 
    	// String body = response.body().prettyPrint();
    	 String body = response.prettyPrint();
    	 System.out.println(body);
    	 
   	 
     }
}
