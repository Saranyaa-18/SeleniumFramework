package learningselenium.tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import learningselenium.TestComponents.BaseTest;
import learningselenium.testing.CartPage;
import learningselenium.testing.OrderConfomationPage;
import learningselenium.testing.OrderPage;
import learningselenium.testing.ProductCatalogue;
import learningselenium.testing.SubmitOrder;

public class endToEndTest extends BaseTest {

	String [] originalValue = {"ZARA COAT 3","IPHONE 13 PRO"};
	
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitTest(HashMap<String,String> input)
	{
		ProductCatalogue productCatalogue = landingPage.loginDetails(input.get("email"),input.get("password"));
		//List<WebElement> productlist = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProduct(input.get("product"));
		Assert.assertTrue(match);
		SubmitOrder submitOrder = cartPage.goToCheckout();
		submitOrder.inputToCountry("ind");
		submitOrder.countryList();
		OrderConfomationPage orderConformationPage = submitOrder.conformationButton();
		String orderConformationText = orderConformationPage.getConformationText();
		Assert.assertEquals(orderConformationText, "THANKYOU FOR THE ORDER.");
		System.out.println("checking push command");
	}
	
	//verify order is there in order history page
	@Test(dependsOnMethods= {"submitTest"})
	public void verifyOrder()
	{
		landingPage.loginDetails("raju.saranyaa18@gmail.com","DancingDoll@18");
		OrderPage orderPage = landingPage.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrders(originalValue[0]));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\learningselenium\\Data\\PurchaseProduct.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}

}
