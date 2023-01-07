package clientapp.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import clientapp.testcomponents.BaseTest;
import purushotham.pageobjects.CartPage;
import purushotham.pageobjects.OrderDetails;
import purushotham.pageobjects.PaymentPage;
import purushotham.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest
{		
	@Test
	public void LoginErrorValidation() throws Exception
	{		
		landingPage.loginApplication("purushothamtest@gmail.com", "Puyru@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test(groups = {"ErrorHandling"})
	public void ProductErrorValidation() throws Exception
	{		
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailId, passwordId);
		List<WebElement> productsList = productCatalogue.getListOfProducts();
		productCatalogue.clickOnAddToCart(targetProduct1);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyTheProductAdded("Addidas");
		Assert.assertFalse(match);
	}
}
