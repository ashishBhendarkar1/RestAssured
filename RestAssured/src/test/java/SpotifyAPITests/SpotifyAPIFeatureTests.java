package SpotifyAPITests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyAPIFeatureTests {
	String token;
	
	@BeforeMethod
	public String getToken() {
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		token = given().log().all()
//		 .contentType(ContentType.URLENC)
		  .contentType("application/x-www-form-urlencoded")		
		  .body("grant_type=client_credentials&client_id=b8171f9bfda1440b995a28a02d5c878b&client_secret=bae8ffaae4044eaabcf2f51a88208409")
		   .when().log().all()
		    .post("/api/token")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200)
		        .and()
		         .extract()
		          .path("access_token");
		
		System.out.println("bearer token is  ==>"+token);
		return token;
	}
	
	@Test
	public void getArtistinfoTest() {
		RestAssured.baseURI = "https://api.spotify.com";
		
		String ArtistName = given().log().all()
		 .header("Authorization","Bearer "+token)
		  .pathParam("ArtistHashid", "4YRxDV8wJFPHPTeXepOstw")
		   .queryParam("si", "OJEw79bUTH22YSmhsVV7JA")
		    .when().log().all()
		     .get("/v1/artists/{ArtistHashid}")
		      .then().log().all()
		       .assertThat()
		        .statusCode(200)
		         .and()
		          .body("id" ,equalTo("4YRxDV8wJFPHPTeXepOstw"))
		           .and()
		            .extract()
		             .path("name");
		
		System.out.println("Artist Name is :"+ArtistName);
		Assert.assertEquals(ArtistName, "Arijit Singh");
	}
	
	@Test
	public void getPlaylistTest() {
		RestAssured.baseURI = "https://api.spotify.com";
		
		given().log().all()
		 .header("Authorization","Bearer "+token)
		  .pathParam("playlistID", "3cEYpjA9oz9GiPac4AsH4n")
		   .when().log().all()
		    .get("/v1/playlists/{playlistID}")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200)
		       .and()
		        .body("description", equalTo("A playlist for testing pourposes"));
	}

//not working
	@Test
	public void getCurrentUserProfileTest() {
		RestAssured.baseURI = "https://api.spotify.com";
		
		given().log().all()
		 .header("Authorization","Bearer "+token)
		   .when().log().all()
		    .get("/v1/me")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200)
		       .and()
		        .body("display_name", equalTo("Ashish"));
	}

}
