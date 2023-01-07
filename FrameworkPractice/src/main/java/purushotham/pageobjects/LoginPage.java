package purushotham.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import purushotham.abstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="userEmail")
	WebElement emailId;
	
	@FindBy(id="userPassword")
	WebElement passWord;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="#toast-container")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		emailId.sendKeys(email);
		passWord.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToBeVisible(errorMessage);
		String errMsg = errorMessage.getText();
		return errMsg;
	}
	
}
