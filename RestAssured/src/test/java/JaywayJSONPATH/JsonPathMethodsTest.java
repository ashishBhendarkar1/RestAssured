package JaywayJSONPATH;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Set;

public class JsonPathMethodsTest {

	@Test
	public void getProductTest() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonstring = response.asString();
		System.out.println(jsonstring);
		ReadContext ctx = JsonPath.parse(jsonstring);

		// min() value from given array of json
		double minprice = ctx.read("min($.[*].price)");
		System.out.println("min price is : " + minprice);
		System.out.println("----------------");

		// max() value from given array of json
		double maxprice = ctx.read("max($.[*].price)");
		System.out.println("max price is : " + maxprice);
		System.out.println("----------------");

		// avg() value from given array of json
		double avgprice = ctx.read("avg($.[*].price)");
		System.out.println("avg price is : " + avgprice);
		System.out.println("----------------");

		// length() value from given array of json
		Integer lenprice = ctx.read("length($)");
		System.out.println("length of array json : " + lenprice);
		System.out.println("----------------");

		// sum() value from given array of json
		double sumprice = ctx.read("sum($.[*].price)");
		System.out.println("sum price is : " + sumprice);
		System.out.println("----------------");

		// keys() Provides the property keys (An alternative for terminal tilde ~)
		Set<String> key = ctx.read("keys($)");
		System.out.println("keys is : " + key);
		System.out.println("----------------");

		// concat(x) Provides a concatinated version of the path output with a new item
		String concatprice = ctx.read("concat($.[1].price,'0.100')");
		System.out.println("concat price is : " + concatprice);
		System.out.println("----------------");

		// append(x) add an item to the json path output array
		Object appendjsonres = ctx.read("append($.[*],'0.100')");
		System.out.println("append json response at end : " + appendjsonres);
		System.out.println("----------------");

		// first() get the index 0 from the json array
		Map<String, Object> first = ctx.read("first($.[*])");
		System.out.println("index 0 or first array is : " + first);
		System.out.println("----------------");

		// last() get the last array from the json array
		// i my case append method is in String
		String last = ctx.read("last($.[*])");
		System.out.println("last array value is : " + last);
		System.out.println("----------------");

		// index(x) Provides the item of an array of index: X, if the X is negative,
		// take from backwards
		Object index = ctx.read("append($.[*].title,2)");
		System.out.println("get index 2 array is : " + index);
		System.out.println("----------------");
	}

}
