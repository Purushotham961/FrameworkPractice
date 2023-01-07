package clientapp.tests;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class ClientAppTest extends BaseTest
{
	@Test()
	public void submitOrder1() throws Exception
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
	
	@Test(dependsOnMethods = {"submitOrder1"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(emailId, passwordId);
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		ordersPage.getListOfOrdersPlaced();
		Boolean match = ordersPage.verifyProduct(targetProduct1);
		Assert.assertTrue(match);
	}

}
