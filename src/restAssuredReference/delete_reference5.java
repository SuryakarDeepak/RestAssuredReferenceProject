package restAssuredReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class delete_reference5 {

	public static void main(String[] args) {
	RestAssured.baseURI="https://reqres.in/";
	
	int statuscode = given().header("content-Type","application/java").when().delete("/api/users/2").then().extract().statusCode();
    Assert.assertEquals(statuscode,204);
    
    System.out.println("status Code :"+ statuscode);
    System.out.println("DELETE METHOD SUCCESSFULLY VALIDATED...!");
    
	}

}