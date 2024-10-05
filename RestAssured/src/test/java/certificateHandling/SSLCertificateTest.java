package certificateHandling;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

public class SSLCertificateTest {

	@Test
	public void sslTest() {
		RestAssured.given()
		            .relaxedHTTPSValidation()  // we need to OFF the cert validation
		             .when()
		              .get("https://expired.badssl.com/")
		               .then().log().all()
		                .assertThat()
		                 .statusCode(200);
	}
	
	@Test
	public void sslTest_With_Config() {
		RestAssured.config()
		              .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
		
		RestAssured.given()
		             .when()
		              .get("https://expired.badssl.com/")
		               .then().log().all()
		                .assertThat()
		                 .statusCode(200);
	}
}
