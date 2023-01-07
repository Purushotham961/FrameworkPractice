package purushotham.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import purushotham.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".cart h3")
	List<WebElement> productsAdded;
	
	@FindBy(css="li[class='totalRow'] button[type='button']")
	WebElement checkoutButton;
	
	By product = By.cssSelector(".cartSection h3");
	
	public List<WebElement> getProductsAdded()
	{
		return productsAdded;
	}
	
	public Boolean verifyTheProductAdded(String productName)
	{
		Boolean match = getProductsAdded().stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage clickOnCheckOut()
	{
		waitForElementToBeClickableByJS(checkoutButton);
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
	
	
	
	
}
