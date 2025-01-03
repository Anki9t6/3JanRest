package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomReportExample {

    // ExtentReports instance
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
	    public void setup() {
	        // Initialize ExtentReports
	        extent = new ExtentReports();
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ExtentReport.html");
	        extent.attachReporter(htmlReporter);
	      //  extent.attachReporter(new com.aventstack.extentreports.reporter.ExtentHtmlReporter("API_Test_Report.html"));
	      //  extent.attachReporter(new com.aventstack.extentreports.reporter.ExtentHtmlReporter("API_Test_Report.html"));
	    }
    @Test
    public void testGETRequest() {
        // Start a new test
        test = extent.createTest("GET Request Test", "Validating the response from GET /users API");

        // Make GET Request
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");

        // Extract response data
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        long responseTime = response.getTime();

        // Custom HTML Table to display additional columns (API Response Time, Status Code, Payload)
        String customTable = "<table border='1' cellpadding='5' cellspacing='0' style='width:100%; margin-top:10px;'>"
                + "<tr><th>Test Case Name</th><th>Status Code</th><th>Response Time</th><th>Response Body</th><th>Request Payload</th></tr>"
                + "<tr>"
                + "<td>GET /users</td>"
                + "<td>" + statusCode + "</td>"
                + "<td>" + responseTime + " ms</td>"
                + "<td><pre><div style=\"height:300px; overflow:scroll;\">" + responseBody + "</pre></td>"
                + "<td>None</td>"  // No payload for GET request
                + "</tr>"
                + "</table>";

        // Log the custom table as part of the test
        test.info("API Response Table: " + customTable);

        // Validate the status code and log the result
        if (statusCode == 200) {
            test.log(Status.PASS, "API Response Successful: Status Code 200");
        } else {
            test.log(Status.FAIL, "API Response Failed: Status Code " + statusCode);
        }
    }

    @Test
    public void testPOSTRequest() {
        // Start the test for POST API call
        test = extent.createTest("POST Request Test", "Validating the response from POST /users API");

        // Request payload for POST
        String jsonBody = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"username\": \"johndoe\",\n" +
                "  \"email\": \"john@example.com\"\n" +
                "}";

        // Make POST Request
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/users");

        // Extract response data
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        long responseTime = response.getTime();

        // Custom HTML Table for POST request
        String customTable = "<table border='1' cellpadding='5' cellspacing='0' style='width:100%; margin-top:10px;'>"
                + "<tr><th>Test Case Name</th><th>Status Code</th><th>Response Time</th><th>Response Body</th><th>Request Payload</th></tr>"
                + "<tr>"
                + "<td>POST /users</td>"
                + "<td>" + statusCode + "</td>"
                + "<td>" + responseTime + " ms</td>"
                + "<td><pre>" + responseBody + "</pre></td>"
                + "<td><pre>" + jsonBody + "</pre></td>"
                + "</tr>"
                + "</table>";

        // Log the custom table to the report
        test.info("API Response Table: " + customTable);

        // Validate the status code and log the result
        if (statusCode == 201) {
            test.log(Status.PASS, "POST API Request Successful: Status Code 201");
        } else {
            test.log(Status.FAIL, "POST API Request Failed: Status Code " + statusCode);
        }
    }

    @AfterClass
    public void tearDown() {
        // Flush the ExtentReports to generate the report
        extent.flush();
    }
}
