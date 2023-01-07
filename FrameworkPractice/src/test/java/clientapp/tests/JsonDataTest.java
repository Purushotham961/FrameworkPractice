package clientapp.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

public class JsonDataTest extends BaseTest
{
	@Test(dataProvider = "getData", groups = "ByJsonData")
	public void submitOrder4(HashMap<String, String> input) throws Exception
	{		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productsList = productCatalogue.getListOfProducts();
		productCatalogue.clickOnAddToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyTheProductAdded(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartPage.clickOnCheckOut();
		paymentPage.selectCountryField(countryName);
		paymentPage.selectCountry(countryName);
		OrderDetails orderDetails = paymentPage.clickOnPlaceOrder();
		orderDetails.getMessage();
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		//getJsonDataToMap() taken from BaseTest
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\clientapp\\data\\JsonDataTest.json");	
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
