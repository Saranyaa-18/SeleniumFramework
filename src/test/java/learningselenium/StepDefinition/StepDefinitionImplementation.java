package learningselenium.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learningselenium.TestComponents.BaseTest;
import learningselenium.testing.CartPage;
import learningselenium.testing.LandingPage;
import learningselenium.testing.OrderConfomationPage;
import learningselenium.testing.ProductCatalogue;
import learningselenium.testing.SubmitOrder;

public class StepDefinitionImplementation extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public OrderConfomationPage orderConformationPage;
	
	@Given("^I landed on Ecommerce site$")
	public void i_landed_on_ecommerce_site() throws IOException
	{
		landingPage = getUrl();
	}
	
	@Given("^I want to login with username (.+) and password (.+)$")
	public void i_want_to__login_with_username_and_password(String email,String password)
	{
		productCatalogue = landingPage.loginDetails(email,password);
	}
	
	@When("^Add product (.+) to the cart$")
	public void add_product_to_the_cart(String productName)
	{
		List<WebElement> productlist = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^checkout with (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProduct(productName);
		Assert.assertTrue(match);
		SubmitOrder submitOrder = cartPage.goToCheckout();
		submitOrder.inputToCountry("ind");
		submitOrder.countryList();
		orderConformationPage = submitOrder.conformationButton();
	}
	
	@Then("verify {string} message is displayed")
	public void verify_message_is_displayed(String strArgs)
	{
		String orderConformationText = orderConformationPage.getConformationText();
		Assert.assertEquals(orderConformationText, strArgs);
		driver.close();
	}
	
	@Then("verify error {string} message is displayed")
	public void verify_error_message_is_displayed(String strArgs)
	{
		Assert.assertEquals(strArgs, landingPage.loginErrorValidation());
		driver.close();
	}
	
}
