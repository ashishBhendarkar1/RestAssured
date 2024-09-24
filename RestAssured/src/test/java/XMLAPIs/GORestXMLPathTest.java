package XMLAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class GORestXMLPathTest {

	@Test
	public void gorestAPITest() {

		RestAssured.baseURI = "https://gorest.co.in";

		Response response = given().when().get("/public/v2/users.xml").then().extract().response();
	
	String responsebody = response.getBody().asString();
	XmlPath Xmlpath = new XmlPath(responsebody);
	
	//fetch objects type="array"
	String objtype = Xmlpath.getString("objects.@type");
	System.out.println("object type is :"+objtype);
	System.out.println("------------");
	
	//fetch id type="integer"
	List<String> idtype= Xmlpath.getList("objects.object.id.@type");
	System.out.println("id type is :"+idtype);
	for(String e : idtype) {
		System.out.println(e);
		Assert.assertEquals(e, "integer");
	}
	
	System.out.println("------------");
	
	//fetch all id value
	List<String> ids = Xmlpath.getList("objects.object.id");
	System.out.println(ids);
	System.out.println(ids.size());
	for (String i : ids) {
		System.out.println(ids.contains("7415666"));
		break;
	}
	System.out.println("------------");
	
	
	}

}
