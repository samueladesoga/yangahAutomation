import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YangahRegistrationTest {
	WebDriver driver; 
	
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
	}
	
	 @Test
	  public void testAUserCanRegisterSuccessfully() {
	    driver.get("https://staging-yangah.herokuapp.com");
	    driver.findElement(By.linkText("JOIN US")).click();;
	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.elementToBeClickable(
	    		       By.cssSelector("button.register")));
	   driver.findElement(By.cssSelector("form[action='/accounts'] input#user_email")).sendKeys(getSaltString()+"@example.com");
	   driver.findElement(By.cssSelector("form[action='/accounts'] input#user_password")).sendKeys("password");
	   driver.findElement(By.cssSelector("form[action='/accounts'] input#user_password_confirmation")).sendKeys("password");
	   driver.findElement(By.cssSelector("form[action='/accounts'] button.register")).click();
	   String expectedMessage = "A message with a confirmation link has been sent to your email address. Please open the link to activate your account.";
	   assertEquals(expectedMessage, driver.findElement(By.id("flash_notice")).getText());
	  }
	 
	 @After
	 public void cleanUp(){ 
		 driver.quit();
	 }
	 
	 protected String getSaltString() {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 18) {
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
	    }

}
