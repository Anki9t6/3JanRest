package tests;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportExcelApi {
	 protected static ExtentReports extent;
	    protected static ExtentTest test;
	   @BeforeClass
	    public void setup() {
	        // Initialize ExtentReports
	        extent = new ExtentReports();
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ExtentReport.html");
	        extent.attachReporter(htmlReporter);
	       // extent.attachReporter(new com.aventstack.extentreports.reporter.ExtentHtmlReporter("API_Test_Report.html"));
	       // extent.attachReporter(new com.aventstack.extentreports.reporter.ExtentHtmlReporter("API_Test_Report.html"));
	    }
	   @AfterClass
	    public static void tearDown() {
	        // Write the results to the report
	        extent.flush();
	    }
	   @Test
	    public void testGet() {
	        // Start a new test in the report
	        test = extent.createTest("Test GET request for users");

	        
	            // Base URI and Request
	            RestAssured.baseURI = "https://reqres.in/api";
	            RequestSpecification httpRequest = RestAssured.given();
	            Response res = httpRequest.queryParam("page", "2").get("/users");

	            // Parse response body
	            String responseBody = res.body().asString();
	            JsonPath jpath = new JsonPath(responseBody);
	            String page = jpath.getString("page");
	            
	            // Log the request and response details
	            test.log(Status.INFO, "Request URL: https://reqres.in/api");
	            test.log(Status.INFO, "Response Status Code: " + res.getStatusCode());

	            // Validate response status and log result
	            if (res.getStatusCode() == 200) {
	                test.log(Status.PASS, "API Response is successful: Status Code 200");
	            } else {
	                test.log(Status.FAIL, "API Response failed: Status Code " + res.getStatusCode());
	            }
	            // Log response time
	            test.log(Status.INFO, "Response Time: " + res.getTime() + " ms");

	            // Capture the response body (or any additional info) if needed
	            test.log(Status.INFO, "Response Body: " + res.getBody().asString());

	            // Add an attachment for response body (optional)
	            test.addScreenCaptureFromPath("response-body.txt", "Response Body");
	    }

	    @Test
	    public void testPost() {
	        // Start a new test in the report
	        test = extent.createTest("Test POST request for creating a user");

	       
	            // Create request body
	            JSONObject request = new JSONObject();
	            request.put("name", "morpheus");
	            request.put("job", "leader");
	            request.put("id", "514");
	            request.put("createdAt", "2024-10-15T08:27:49.349Z");

	            // Base URI and POST request
	            RestAssured.baseURI = "https://reqres.in/api";
	            Response res = given().body(request.toJSONString()).when().post("/users");
	         // Log the request and response details
	            test.log(Status.INFO, "Request URL: https://jsonplaceholder.typicode.com/users");
	            test.log(Status.INFO, "Request Payload: " + request);
	            test.log(Status.INFO, "Response Status Code: " + res.getStatusCode());

	            // Validate response status and log result
	            if (res.getStatusCode() == 201) {
	                test.log(Status.PASS, "POST API Request Successful: Status Code 201");
	            } else {
	                test.log(Status.FAIL, "POST API Request Failed: Status Code " + res.getStatusCode());
	            }

	            // Log response body (useful for debugging)
	            test.log(Status.INFO, "Response Body: " + res.getBody().asString());
	        }
	        }


