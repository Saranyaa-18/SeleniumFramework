package learningselenium.testing;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningselenium.AbstractComponent.AbstractComponent;


public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderLists;
	
	public Boolean verifyOrders(String originalValue)
	{
		Boolean match = orderLists.stream().anyMatch(cartList->cartList.getText().equalsIgnoreCase(originalValue));
		return match;
	}
	

}
