package purushotham.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import purushotham.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="div[aria-label*='Cart']")
	WebElement popupMsg;
	
	By productsBy= By.cssSelector(".mb-3");
	By addToCartButton = By.cssSelector("[class='btn w-10 rounded']");
	By productText = By.cssSelector("b");
	
	public List<WebElement> getListOfProducts()
	{
		waitForListOfWebElementToAppear(products);
		return products;
	}
	
	public WebElement getProductByName(String targetProductName)
	{
		waitForListOfWebElementToAppear(getListOfProducts());
		WebElement prod = getListOfProducts().stream()
				.filter(product->product.findElement(productText).getText().equalsIgnoreCase(targetProductName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void clickOnAddToCart(String targetProductName1) throws Exception
	{
		WebElement prod = getProductByName(targetProductName1);
		waitForElementToBeVisible(addToCartButton);
		prod.findElement(addToCartButton).click();
		Thread.sleep(1000);
	}	
}
