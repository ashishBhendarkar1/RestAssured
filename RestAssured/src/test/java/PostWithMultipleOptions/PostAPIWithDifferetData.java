package PostWithMultipleOptions;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.File;

public class PostAPIWithDifferetData {
	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		String payload = "hi this is my text content type payload";
		
		given().log().all()
		 .contentType(ContentType.TEXT)
		  .body(payload)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		String payload = "<script>\r\n" + 
				"function myFunction() {\r\n" + 
				"  document.getElementById(\"demo\").innerHTML = \"Paragraph changed.\";\r\n" + 
				"}\r\n" + 
				"</script>";
		
		given().log().all()
		 .contentType("application/javascript")
		  .body(payload)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void bodyWithHTMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		String payload = "<html>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<h2>What Can JavaScript Do?</h2>\r\n" + 
				"\r\n" + 
				"<p id=\"demo\">JavaScript can change HTML content.</p>\r\n" + 
				"\r\n" + 
				"<button type=\"button\" onclick='document.getElementById(\"demo\").innerHTML = \"Hello JavaScript!\"'>Click Me!</button>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		given().log().all()
		 .contentType(ContentType.HTML)
		  .body(payload)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		String payload = "<dependency>\r\n" + 
				"			<groupId>io.rest-assured</groupId>\r\n" + 
				"			<artifactId>rest-assured</artifactId>\r\n" + 
				"			<version>5.5.0</version>\r\n" + 
				"		</dependency>\r\n" + 
				"";
		
		given().log().all()
		 .contentType("application/xml;charset=utf-8")
		  .body(payload)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void bodyWithMultiPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
				
		given().log().all()
		 .contentType(ContentType.MULTIPART)
		  .multiPart("filename", new File("C:\\Users\\ashish\\Desktop\\APInotes\\APITestData\\myuser.csv"))
		   .multiPart("name", "user file")
		    .when().log().all()
		     .post("/post")
		      .then().log().all()
		       .assertThat()
		        .statusCode(200);
		
	}
	
	@Test
	public void bodyWithPDFTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
				
		given().log().all()
		 .contentType("application/pdf")
		  .body(new File("C:\\Users\\ashish\\Desktop\\FBP proof\\postpaid\\2023\\dec 23.pdf"))
		    .when().log().all()
		     .post("/post")
		      .then().log().all()
		       .assertThat()
		        .statusCode(200);
		
	}

}
