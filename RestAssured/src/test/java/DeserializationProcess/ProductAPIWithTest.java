package DeserializationProcess;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

public class ProductAPIWithTest {
	
	@Test
	public void getProductsTestWith_POJO_Deserialization() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		
		Response response = given()
		 .when().log().all()
		  .get("/products");
		
		response.prettyPrint();
		  
		//De-serialization responseJSON to POJO(product class)
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product[] productres = mapper.readValue(response.body().asString(),Product[].class);
		
			System.out.println(Arrays.toString(productres));
			System.out.println("===================");
			for (Product p : productres) {
				System.out.println("id :" + p.getId());
				System.out.println("title :" + p.getTitle());
				System.out.println("price :" +p.getPrice());
				System.out.println("rate :"+p.getRating().getRate());
				System.out.println("count :"+p.getRating().getCount());
				
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
