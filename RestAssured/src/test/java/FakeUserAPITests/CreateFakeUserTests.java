package FakeUserAPITests;

import org.testng.annotations.Test;

import FakeUserAPITests.User.Address.Geolocation;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateFakeUserTests {
	
	@Test
	public void createUserTest_LombokPOJO() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
//		Geolocation geolocation = new Geolocation("-98.900", "987.999");
		
		User.Address.Geolocation geolocation = new User.Address.Geolocation("-98.900", "987.999"); 
		
		User.Address address= new User.Address("pune", "nagar road", 9898, "9999", geolocation);
		
		User.Name name = new User.Name("ASHISH", "BHENDARKAR");
		
		User user = new User("ashish@gmail.com", "Ashish", "ashish@123", "1235432", name, address);
		
	given().log().all()
		.body(user)
		 .when().log().all()
		  .post("/users")
		   .then().log().all()
		    .assertThat()
		     .statusCode(200);
		
	}
	
	@Test
	public void createUserTest_Lombok_Builder() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		User.Address.Geolocation geolocation = new User.Address.Geolocation.GeolocationBuilder()
                              .lat("-98.98")
                              .longitude("199.99")
                              .build();
		
		User.Address address = new User.Address.AddressBuilder()
		                      .city("pune")
		                      .street("old road")
		                      .zipcode("1245")
		                      .number(789)
		                      .geolocation(geolocation)
		                      .build();
		User.Name name = new User.Name.NameBuilder()
		                       .firstname("abc")
		                       .lastname("xyz")
		                       .build();
		User user = new User.UserBuilder()
		                .address(address)
		                .email("abc@gmail.com")
		                .name(name)
		                .password("abc@123")
		                .phone("122456")
		                .username("alpha")
		                .build();
		                
		
		
	given().log().all()
		.body(user)
		 .when().log().all()
		  .post("/users")
		   .then().log().all()
		    .assertThat()
		     .statusCode(200);
		
	}
	

}
