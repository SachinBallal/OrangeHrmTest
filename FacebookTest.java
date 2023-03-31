package orangetest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
public class FacebookTest {
	WebDriver d;
	@Test
	public void login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("-remote-allow-origins=*");
		d = new ChromeDriver(opt);
		d.get("https://www.facebook.com/");
		Thread.sleep(3000);
		d.findElement(By.name("email")).sendKeys("12345678901234567890", Keys.ENTER);
		Thread.sleep(3000);
		SoftAssert softassert = new SoftAssert();
		String actualTitle = d.getTitle();
		
		String expectTitle = "Login in to Facebook";
		softassert.assertEquals(actualTitle, expectTitle, "Title mismatch");
		
		String actualurl = d.getCurrentUrl();
		String expecturl = "https://www.facebook.com/login/123";
		softassert.assertNotEquals(actualurl, expecturl, "url mismatch");
		
		String actualText = d.findElement(By.name("email")).getAttribute("value");
		String expectText = "";
		softassert.assertEquals(actualText, expectText, "text mismatch");
		
		String actualBorder = d.findElement(By.name("email")).getCssValue("border");
		String expectBorder = "1px solid rgb(240, 40, 73)";
		softassert.assertNotEquals(actualBorder, expectBorder, "border mismatch");
		
		String actualErrorText = d.findElement(By.xpath("//div[@id='email_container']/div[last()]")).getText();
		String expectErrorText = "The email address or mobile number you entered isn't connected to an account. Find your account and log in.";
		softassert.assertEquals(actualErrorText, expectErrorText, "ErrorText mismatch");
		
		d.quit();
		softassert.assertAll();
	}
	
}
