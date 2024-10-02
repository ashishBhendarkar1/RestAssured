package Auth;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OAuth1APITest {
	
	@Test
	public void flickAPITest() {
		RestAssured.baseURI = "https://www.flickr.com";
		
		Response response = given()
		 .auth()
		  .oauth("a110d09788adcf0a3f867e4958a0a3ef",
				  "ecb0b3f9ab7d9f8c",
				  "72157720929991962-e1c25ed42b7c024c",
				  "0f4a1d7993ecac89")
		    .queryParam("nojsoncallback", 1)
		     .queryParam("format", "json")
		      .queryParam("method", "flickr.test.login")
               .when()
                .get("/services/rest")
                 .then()
                  .assertThat()
                   .statusCode(200)
                    .extract()
                     .response();
	
		response.prettyPrint();
		
		String resstring = response.asString();
		ReadContext rc = JsonPath.parse(resstring);
		
		String username = rc.read("$.user.username._content");
		System.out.println("User name = " +username);
	}

}
