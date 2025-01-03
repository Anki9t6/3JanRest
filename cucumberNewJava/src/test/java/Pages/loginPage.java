package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
	By txt_username=By.name("username");
	By txt_password=By.name("password");
	By txt_loginbtn=By.xpath("/html/body/form/input[3]");
	WebDriver driver;
	
	public loginPage(WebDriver driver) {
		this.driver=driver;
	}
	public void enterusername(String username) {
		driver.findElement(txt_username).sendKeys(username);
		
	}
	public void enterpassword(String password) {
		driver.findElement(txt_password).sendKeys(password);
		
	}
	public void clickLogin() {
		driver.findElement(txt_loginbtn).click();
	}
}
