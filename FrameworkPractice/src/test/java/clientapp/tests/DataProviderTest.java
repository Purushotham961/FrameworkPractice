package clientapp.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import clientapp.testcomponents.BaseTest;
import purushotham.pageobjects.CartPage;
import purushotham.pageobjects.OrderDetails;
import purushotham.pageobjects.OrdersPage;
import purushotham.pageobjects.PaymentPage;
import purushotham.pageobjects.ProductCatalogue;

public class DataProviderTest extends BaseTest
{
	@Test(dataProvider = "getData", groups = "ByDataProvider", dependsOnMethods = {"DataProviderTestDepends"})
	public void submitOrder2(String email, String password, String productName) throws Exception
	{		
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		List<WebElement> productsList = productCatalogue.getListOfProducts();
		productCatalogue.clickOnAddToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyTheProductAdded(productName);
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartPage.clickOnCheckOut();
		paymentPage.selectCountryField(countryName);
		paymentPage.selectCountry(countryName);
		OrderDetails orderDetails = paymentPage.clickOnPlaceOrder();
		orderDetails.getMessage();
	}
	@Test()
	public void DataProviderTestDepends() throws Exception
	{		
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailId, passwordId);
		List<WebElement> productsList = productCatalogue.getListOfProducts();
		productCatalogue.clickOnAddToCart(targetProduct1);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match1 = cartPage.verifyTheProductAdded(targetProduct1);
		Assert.assertTrue(match1);
		PaymentPage paymentPage = cartPage.clickOnCheckOut();
		paymentPage.selectCountryField(countryName);
		paymentPage.selectCountry(countryName);
		OrderDetails orderDetails = paymentPage.clickOnPlaceOrder();
		orderDetails.getMessage();
	}
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"purushothamtest@gmail.com", "Puru@12345", "ADIDAS ORIGINAL"},{"purushotham@gmail.com", "Puru@12345", "ZARA COAT 3"}};
	}
}
