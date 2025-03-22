package learningselenium.testing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import learningselenium.AbstractComponent.AbstractComponent;


public class SubmitOrder extends AbstractComponent{

	WebDriver driver;
	
	public SubmitOrder(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement inputToCountry;
	
	@FindBy(css=".list-group span[class='ng-star-inserted']")
	List<WebElement> countryList;
	
	@FindBy(css=".btnn")
	WebElement conformationButton;
	
	By countryWait = By.cssSelector(".list-group");
	
	public void inputToCountry(String inputCountry)
	{
		inputToCountry.sendKeys(inputCountry);
		waitForElementToAppear(countryWait);
	}
	
	public void countryList()
	{
		countryList.stream().filter(list->list.getText().equals("India")).findFirst().orElse(null).click();
	}
	
	public OrderConfomationPage conformationButton()
	{
		conformationButton.click();
		return new OrderConfomationPage(driver);
	}
	
}
