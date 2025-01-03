package StepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchStep {
	WebDriver driver=null;
	
	@Given("browser is open")
	public void browser_is_open() {
System.getProperty("webdriver.chrome.driver","C:/Users/ankit.sh/eclipse-workspace/cucumberNewJava/src/test/resources/Drivers/chromedriver.exe");
driver=new ChromeDriver();
System.out.println("inside given");
	}

	@And("user is on google search box")
	public void user_is_on_google_search_box() {
	   System.out.println("inside given and");
	   driver.navigate().to("https://www.google.com/");
	   
	   System.out.println("inside given and");
	}

	@When("user enter a text in search box")
	public void user_enter_a_text_in_search_box() {
		driver.findElement(By.name("q")).sendKeys("automation step by step");
		System.out.println("inside when");
	}

	@And("Hits enters")
	public void hits_enters() {
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		System.out.println("Inside when and");
	}

	@Then("user is navigate to search result")
	public void user_is_navigate_to_search_result() {
		driver.getPageSource().contains("Online Courses");
		System.out.println("inside then");
	}


}
