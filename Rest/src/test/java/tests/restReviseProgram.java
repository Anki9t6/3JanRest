package tests;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;

import io.restassured.response.Response;


public class restReviseProgram {
	private static String name;
	private static String job;
	@Test(priority = 0)
	public void getTheUsrDetail() {
	
	
	
	
	Response response=given().
			when().get("https://reqres.in/api/users?page=2");
 name=response.jsonPath().getString("data[0].first_name");
 job=response.jsonPath().getString("data[0].last_name");
 
   System.out.println("lastname: " + job);
    System.out.println("valua of first data  "+name);

  
	}
			
@Test(priority = 1)
public void createNewUser() {
	
	   System.out.println("create mai value for job " + job);
	    System.out.println("create mai valua of first data  "+name);
	pozoClass user = new pozoClass();
	user.setName(name);
	user.setJob(job);
	
	  System.out.println("dfg  " + job);
	    System.out.println("hhhh  "+name);
	
	   given()
              .contentType("application/json")
              .body(user)
              .when()
              .post("https://reqres.in/api/users").
              then().statusCode(201).log().all();
}
}
