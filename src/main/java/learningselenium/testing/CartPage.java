package learningselenium.testing;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartLists;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOut;
	
	public Boolean verifyProduct(String originalValue)
	{
		Boolean match = cartLists.stream().anyMatch(cartList->cartList.getText().equalsIgnoreCase(originalValue));
		return match;
	}
	
	public SubmitOrder goToCheckout()
	{
		checkOut.click();
		SubmitOrder submitOrder = new SubmitOrder(driver);
		return submitOrder;
	}

}
