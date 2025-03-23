package learningselenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import learningselenium.TestComponents.BaseTest;
import learningselenium.TestComponents.Retry;
import learningselenium.testing.CartPage;
import learningselenium.testing.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void pageLoginError()
	{
		landingPage.loginDetails("raju.saranyaa@gmail.com","Dancingdoll@18");
		Assert.assertEquals("Incorrect email or password.", landingPage.loginErrorValidation());
	}
	
	@Test(groups= {"ErrorHandling"})
	public void pageCatalogueError() throws InterruptedException
	{
		String [] originalValue = {"ZARA COAT 3","IPHONE 13 PRO"};
		ProductCatalogue productCatalogue = landingPage.loginDetails("raju.saranyaa01@gmail.com","DancingDoll@01");
		productCatalogue.getProductName(originalValue[0]);
		productCatalogue.addProductToCart(originalValue[0]);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
