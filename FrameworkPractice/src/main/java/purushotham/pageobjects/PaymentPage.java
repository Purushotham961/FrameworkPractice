package purushotham.pageobjects;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import purushotham.abstractcomponents.AbstractComponent;

public class PaymentPage extends AbstractComponent
{
	WebDriver driver;
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement countrytextField;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	@FindBy(css=".form-group section button:nth-child(3)")
	List<WebElement> countrysDropdown;
	
	public void selectCountryField(String countryName) throws Exception
	{
		countrytextField.sendKeys(countryName);
	}
	
	public List<WebElement> getCountrysList()
	{
		return countrysDropdown;
	}
	
	public void selectCountry(String countryName)
	{
		for (WebElement country : countrysDropdown) 
		{
			if(country.getText().equalsIgnoreCase(countryName))
			{
				country.click();
			}
		}
	}
	
	public OrderDetails clickOnPlaceOrder()
	{
		waitForElementToBeClickableByJS(placeOrder);
		OrderDetails orderDetails = new OrderDetails(driver);
		return orderDetails;
	}

	
}
