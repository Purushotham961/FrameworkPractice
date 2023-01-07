package clientapp.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Script 
{
	static String targetProductName = "ADIDAS ORIGINAL";
	public static void main(String[] args) throws Exception 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("purushothamtest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Puru@12345");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
		.filter(product->product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(targetProductName))
		.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector("[class='btn w-10 rounded']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[aria-label*='Cart']"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> productsAdded = driver.findElements(By.cssSelector("[class*='items even']"));
		
		Boolean match = productsAdded.stream().anyMatch(product->product.findElement(By.cssSelector(".cartSection h3")).getText().equalsIgnoreCase(targetProductName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		Thread.sleep(1000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.cssSelector(".action__submit")).click();
		String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(msg);
		driver.quit();
	}
}
