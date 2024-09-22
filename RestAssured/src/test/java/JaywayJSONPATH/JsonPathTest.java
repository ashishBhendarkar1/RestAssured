package JaywayJSONPATH;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonPathTest {

	@Test
	public void getProductAPITest_JsonPath() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		// ReadContext ctx = JsonPath.parse(jsonResponse).read("$[?(@.price >
		// 50)].title");

		// Single attribute fetching

		ReadContext ctx = JsonPath.parse(jsonResponse);
		List<Number> prices = ctx.read("$[?(@.price > 50)].price");
		System.out.println(prices);
		for (Number number : prices) {
			System.out.println(number);
		}
		System.out.println("----------------");

		// IDS
		List<Integer> ids = ctx.read("$[?(@.price > 50)].id");
		System.out.println(ids);
		System.out.println("----------------");

		// title
		List<String> title = ctx.read("$[?(@.price > 50)].title");
		System.out.println(title);
		System.out.println("----------------");

		// rate
		List<Double> rate = ctx.read("$[?(@.price > 50)].rating.rate");
		System.out.println(rate);
		System.out.println("----------------");

		// count
		List<Integer> count = ctx.read("$[?(@.price > 50)].rating.count");
		System.out.println(count);
		System.out.println("----------------");

		for (int i = 0; i < ids.size(); i++) {
			System.out.println(ids.get(i) + " - " + title.get(i));
		}
		System.out.println("----------------");

		// rating.rate
		List<Integer> rates = ctx.read("$.[?(@.rating.rate < 3)].id");
		System.out.println(rates);
		System.out.println("----------------");
	}

	@Test
	public void productApiTest_ConditionalExamples() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		ReadContext ctx = JsonPath.parse(jsonResponse);

		// 2 or more attribute fetching
		List<Map<String, Object>> jeweleryList = ctx.read("$.[?(@.category == 'jewelery')].['title','price']");
		System.out.println(jeweleryList);
		System.out.println("size is :"+jeweleryList.size());
		System.out.println("----------------");
		
		for (Map<String, Object> product : jeweleryList) {
		String title = (String)product.get("title");
		Number price = (Number)product.get("price");
		System.out.println("title : "+title);
		System.out.println("price : "+price);
		}
		
		List<Map<String, Object>> jeweleryList1 = ctx.read("$.[?(@.category == 'jewelery')].['title','price','id']");
		System.out.println("----------------");
		
		for (Map<String, Object> product : jeweleryList1) {
		String title = (String)product.get("title");
		Number price = (Number)product.get("price");
		Integer id = (Integer)product.get("id");
		System.out.println("title : "+title);
		System.out.println("price : "+price);
		System.out.println("id : "+id);
		}
			
		
		// with 2 conditions
		//$.[?((@.category == 'jewelery') && (@.price > 50))].['title','id']
		
		//nested attr.
		//$.[?((@.rating.rate >3) && (@.price > 20))].['title','id']
		}
	}


