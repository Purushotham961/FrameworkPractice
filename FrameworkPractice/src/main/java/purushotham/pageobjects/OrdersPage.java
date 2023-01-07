package purushotham.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import purushotham.abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent
{
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".table-hover tbody td:nth-child(3)")
	List<WebElement> productNames;
	
	public List<WebElement> getListOfOrdersPlaced()
	{
		waitForListOfWebElementToAppear(productNames);
		return productNames;
	}
	public Boolean verifyProduct(String targetProductName)
	{
		Boolean match = getListOfOrdersPlaced().stream().anyMatch(product->product.getText().equalsIgnoreCase(targetProductName));
		return match;
	}
	
}
