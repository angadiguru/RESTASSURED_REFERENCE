import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class Rest_Post_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		//Declare the request body string variable
		String RequestBody = "{\r\n"
				+ "    \"name\": \"guru\",\r\n"
				+ "    \"job\": \"tester\"\r\n"
				+ "}";
		
		System.out.println(RequestBody);
		
		//Declare the expected results
		JsonPath JspRequest = new JsonPath(RequestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0, 11);
		
		//Declare given, when then method (Response Body fetching)
		String ResponseBody = given().header("Content-Type","application/json").body(RequestBody).
				when().post("api/users").then().extract().response().asPrettyString();
		
		int statusCode = given().header("Content-Type","application/json").body(RequestBody).
				when().post("api/users").then().extract().response().statusCode();
		System.out.println(statusCode);
		System.out.println(ResponseBody);
		
		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_createdAt = JspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0,11);
		
		//Validate the ResponseBody parameters
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_createdAt, expecteddate);
		
		
	}

}
