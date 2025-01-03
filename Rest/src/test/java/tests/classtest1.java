package tests;

import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;

import io.restassured.response.Response;

public class classtest1 {
		
@Test
public void test() {
	Response response=get("https://reqres.in/api/users?page=2"); 
	
System.out.println(response.getBody().asString());
response.getStatusCode();
response.getTime();

}	
@Test
public void putTest() {
	baseURI="https://reqres.in/api";
	given().get("users?page=2").then().
	statusCode(200).body("data[4].first_name", equalTo("George"),"data[1].last_name",equalTo("Ferguson"))
	.body("data", hasItems("Lindsay","Ferguson")).log().all();
	
	
}	
@Test
public void postTest() {
	JSONObject request=new JSONObject();
	request.put( "name","morpheus");
	request.put("job", "leader");
	request.put("id", "514");
request.put("createdAt", "2024-10-15T08:27:49.349Z");
	baseURI="https://reqres.in/api";
	given().body(request.toJSONString()).when().post("/users").then().
	statusCode(201).log().all();
	
	
}	
}
