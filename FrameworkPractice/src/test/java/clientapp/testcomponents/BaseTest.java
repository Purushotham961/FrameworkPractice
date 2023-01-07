package clientapp.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import purushotham.abstractcomponents.AbstractComponent;
import purushotham.pageobjects.LoginPage;

public class BaseTest
{
	public static String browserName;
	public static String targetProduct1;
	public static String targetProduct2;
	public static String emailId;
	public static String passwordId;
	public static String countryName;
	
	public WebDriver driver;
	public LoginPage landingPage;
	public WebDriver initializeDriver() throws Exception
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\purushotham\\resources\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		browserName = prop.getProperty("browser");
		targetProduct1 = prop.getProperty("targetProductName1");
		targetProduct2 = prop.getProperty("targetProductName2");
		emailId = prop.getProperty("email");
		passwordId = prop.getProperty("password");
		countryName = prop.getProperty("countryName");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws Exception
	{
		driver = initializeDriver();
		landingPage = new LoginPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws Exception
	{
		//json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap -> Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
	}
	
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws Exception
	{
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"//Screenshots//"+testcaseName+".png");
		FileUtils.copyFile(srcFile, destFile);
		return System.getProperty("user.dir")+"//Screenshots//"+testcaseName+".png";
	}
}
