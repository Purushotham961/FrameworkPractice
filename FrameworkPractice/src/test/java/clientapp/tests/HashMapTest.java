package clientapp.tests;

import java.util.HashMap;
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

public class HashMapTest extends BaseTest
{
	@Test(dataProvider = "getData", groups = "ByHashMap")
	public void submitOrder3(HashMap<String, String> input) throws Exception
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
	public Object[][] getData()
	{
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "purushothamtest@gmail.com");
		map1.put("password", "Puru@12345");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "purushotham@gmail.com");
		map2.put("password", "Puru@12345");
		map2.put("productName", "ZARA COAT 3");
		
		
		return new Object[][] {{map1},{map2}};
	}
}
