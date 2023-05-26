package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import java.time.LocalDateTime;
public class post_reference1 {

	public static void main(String[] args) {
		String BaseURI="https://reqres.in/";
		String RequestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		LocalDateTime Date = LocalDateTime.now();
		String expectDate = Date.toString().substring(0,11);
		
		JsonPath jsprerequest = new JsonPath(RequestBody);
		String req_name = jsprerequest.getString("name");
		String req_job = jsprerequest.getString("job");
		String req_email = jsprerequest.getString("email");
		
		RestAssured.baseURI= BaseURI;
		
		int statuscode = given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users").then().extract().statusCode();
		String responseBody = given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users").then().extract().response().asString();
		System.out.println("status Code :"+ statuscode);
		System.out.println("status Body :"+ responseBody);
		
		JsonPath jsp = new JsonPath(responseBody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id= jsp.getString("id");
		String res_email = jsp.getString("email");
		String res_createdAt = jsp.getString("createdAt");
		res_createdAt = res_createdAt.substring(0,11);
		
		Assert.assertEquals(statuscode, 201);
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_job,req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_email,req_email);
		Assert.assertEquals(res_createdAt, expectDate);
		
		System.out.println("Date :"+ expectDate);
		System.out.println("POST METHOD SUCCESSFULLY VALIDATED");
		
	}

}