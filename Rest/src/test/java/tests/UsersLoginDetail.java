package tests;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
public class UsersLoginDetail {

	@Test
	public void getAllUser() {
		baseURI="https://reqres.in/api";
	 	Response response=given().when()
		.get("/users?page=2");
	 	response.getBody();
		String responseArray=response.asPrettyString();
		
		

        Gson gson = new Gson();        

        // Parse JSON string to JSON object

       JsonObject jsonObject = gson.fromJson(responseArray, JsonObject.class); 
      
      // System.out.println(jsonObject.getAsJsonObject("page.data"));
       
        //System.out.println(jsonObject);
        System.out.println( jsonObject.get("data/email"));
        
        
	
		//JSONObject requestBody=(JSONObject) response.getBody();
	//	System.out.println(response.asPrettyString());
	}
	/*@Test
	public void getSingleUser() {
		JSONObject request=new JSONObject();
		request.put( "name","morpheus");
		request.put("job", "leader");
		request.put("id", "514");
	request.put("createdAt", "2024-10-15T08:27:49.349Z");
		baseURI="https://reqres.in/api";
	Response response=	 given().body(request.toJSONString()).when().post("/users");

		
	}*/
}
