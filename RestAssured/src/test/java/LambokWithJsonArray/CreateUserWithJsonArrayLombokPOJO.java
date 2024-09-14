package LambokWithJsonArray;

import java.util.Arrays;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class CreateUserWithJsonArrayLombokPOJO {
	
	@Test
	public void createUsesWithJsonArrayLombokPOJO() {
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData userdata1 = new User.UserData(1, "ashish@gmail.com", "ashish", "bhendarkar", "https://reqres.in/img/faces/1-images.jpg");
		User.UserData userdata2 = new User.UserData(2, "piku@gmail.com", "piku", "bhendarkar", "https://reqres.in/img/faces/2-images.jpg");
		User.UserData userdata3 = new User.UserData(3, "prisha@gmail.com", "prisha", "bhendarkar", "https://reqres.in/img/faces/3-images.jpg");
		
		User.Support support = new User.Support("https://reqres.in/#support-heading", "To keep reqres free, contributions towards server costs are appreciated");
		
		User user = new User(1, 6, 12, 2, Arrays.asList(userdata1,userdata2,userdata3), support);
		
		given().log().all()
		 .contentType(ContentType.JSON)
		  .body(user)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void createUserWithJsonArray_LombokBuilder() {
		RestAssured.baseURI = "http://httpbin.org";
		
		User.UserData userdata1 =	new User.UserData.UserDataBuilder()
		                       .id(1)
		                       .email("abc@gmail.com")
		                       .first_name("abc")
		                       .last_name("abc")
		                       .avatar("https://reqres.in/img/faces/1-images.jpg")
		                       .build();
		
		User.UserData userdata2 =	new User.UserData.UserDataBuilder()
                .id(1)
                .email("xyz@gmail.com")
                .first_name("xyz")
                .last_name("xyz")
                .avatar("https://reqres.in/img/faces/2-images.jpg")
                .build();
		
		User.Support support = new User.Support.SupportBuilder()
		        .url("https://reqres.in/#support-heading")
		        .text("To keep reqres free, contributions towards server costs are appreciated")
		        .build();
		
		User user = new User.UserBuilder()
		            .page(1)
		            .per_page(6)
		            .total(12)
		            .total_pages(2)
		            .data(Arrays.asList(userdata1,userdata2))
		            .support(support)
		            .build();
		
		given().log().all()
		 .contentType(ContentType.JSON)
		  .body(user)
		   .when().log().all()
		    .post("/post")
		     .then().log().all()
		      .assertThat()
		       .statusCode(200);
		
	}
	
	@Test
	public void createAnimalUserWithJsonArray_LombokBuilder() {
		RestAssured.baseURI = "http://httpbin.org";
	
		AnimalUser.Tag tag = new AnimalUser.Tag.TagBuilder()
		                          .id(1)
		                          .name("pop")
		                          .build();
		
		AnimalUser.Category category = new AnimalUser.Category.CategoryBuilder()
		                        .id(0)
		                        .name("Animal")
		                        .build();
		
		AnimalUser animaluser = new AnimalUser.AnimalUserBuilder()
		                        .id(200)
		                        .category(category)
		                        .name("Dog")
		                        .photourls(Arrays.asList("https://ex.com","https://dog.com"))
		                        .tags(Arrays.asList(tag))
		                        .status("available")
		                        .build();
		
		given().log().all()
		   .contentType(ContentType.JSON)
		    .body(animaluser)
		     .when().log().all()
		      .post("/post")
		       .then().log().all()
		        .assertThat()
		         .statusCode(200);
		
	}
}
