package StepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginDemoStepsPOM {
	WebDriver driver=null;
	loginPage login;
	
	@Given("default browser is open")
	public void default_browser_is_open() {
		System.out.println("I am inside pom class+++++++++++");
		System.getProperty("webdriver.chrome.driver","C:/Users/ankit.sh/eclipse-workspace/cucumberNewJava/src/test/resources/Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		System.out.println("inside given");	}

	@And("user is on login page2")
	public void user_is_on_login_page2() {
		System.out.println("inside given and");
		   driver.navigate().to("https://www.stealmylogin.com/demo.html");
		   
		   System.out.println("inside given and");
	}

	@When("user enter (.*) and (.*)$")
	public void user_enter_username_and_password(String username ,String password) {
		 login=new loginPage(driver);
		 login.enterusername(username);
		 login.enterpassword(password);
		 login.clickLogin();
	    System.out.println("inside when");
	}

	@Then("user is navigate to home1 page")
	public void user_is_navigate_to_home1_page() {
	   driver.getPageSource().contains("Example Domain") ;
	}


}
