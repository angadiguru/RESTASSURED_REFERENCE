import io.restassured.RestAssured; 
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.xml.*;

public class Soap_API_Reference {

	public static void main(String[] args) {
		// Declare base URL
		RestAssured.baseURI="https://www.dataaccess.com/";
		//Declare the RequestBody 
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>125</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ "";
		System.out.println(RequestBody);
		//Extract response Body
		String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).
				when().post("webservicesserver/NumberConversion.wso").
				then().extract().response().asString();
		System.out.println(ResponseBody);
		//create an object of XML path to parse the response body
		XmlPath XmlResponse=new XmlPath(ResponseBody);
		String Res_parameter=XmlResponse.getString("NumberToWordsRsult");
		System.out.println(Res_parameter);
		//validate the response body
		Assert.assertEquals(Res_parameter,"one hundred and twenty five ");
		
	}

}
