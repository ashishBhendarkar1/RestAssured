package XMLAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class XMLPathTest {

	@Test
	public void circuitApiTest_xml() {

		RestAssured.baseURI = "http://ergast.com";

		Response response = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String responsebody = response.body().asString();
		System.out.println(responsebody);

		// create a object of xmlpath
		XmlPath Xmlpath = new XmlPath(responsebody);

		List<String> circuitname = Xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("total size: " + circuitname.size());
		System.out.println("------------");
		for (String e : circuitname) {
			System.out.println("circ_name : " + e);
		}
		System.out.println("------------");

		// fetch Locality for cricuit circuitId="americas"
		String locality = Xmlpath.getString("**.findAll{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println(locality);
		System.out.println("------------");

		// fetch Country for cricuit circuitId="bahrain"
		String country = Xmlpath.getString("**.findAll{ it.@circuitId == 'bahrain' }.Location.Country");
		System.out.println(country);
		System.out.println("------------");

		// fetch Locality for cricuit circuitId="americas" or circuitId="catalunya" ==>
		// Austin,Montmeló
		List<String> localitylist = Xmlpath
				.getList("**.findAll{ it.@circuitId == 'americas' || it.@circuitId == 'catalunya' }.Location.Locality");
		System.out.println(localitylist);
		System.out.println("------------");

	}

	@Test
	public void xmlpathtest() {
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().when().get("/api/f1/2017/circuits.xml").then().extract().response();

		String responsebody = response.body().asString();
		System.out.println(responsebody);

		// create a object of xmlpath
		XmlPath Xmlpath = new XmlPath(responsebody);
		System.out.println("------------");

		// fetch all circuitId
		List<String> circuitIds = Xmlpath.getList("MRData.CircuitTable.Circuit.@circuitId");
		for (String e : circuitIds) {
			System.out.println(e);
		}
		System.out.println("------------");

		// fetch lat and long values for circuitId="bahrain"
		Double lat = Xmlpath.getDouble("**.findAll{ it.@circuitId == 'bahrain'}.Location.@lat");
		System.out.println("lat is :" + lat);
		Double longitude = Xmlpath.getDouble("**.findAll{ it.@circuitId == 'bahrain'}.Location.@long");
		System.out.println("long is :" + longitude);
		System.out.println("------------");

		// fetch lat and long values for circuitId="bahrain" or circuitId="baku"
		List<String> latvalue = Xmlpath.getList("**.findAll{ it.@circuitId == 'bahrain' || it.@circuitId=='baku'}.Location.@lat");
		System.out.println("lat is :" + latvalue);
		List<String> longvalue = Xmlpath.getList("**.findAll{ it.@circuitId == 'bahrain' || it.@circuitId=='baku' }.Location.@long");
		System.out.println("long is :" + longvalue);
		System.out.println("------------");
		
		// fetch url values for circuitId="americas"
		String url = Xmlpath.getString("**.findAll{ it.@circuitId == 'americas'}.@url");
		System.out.println("americas url is :"+url);

	}

}
