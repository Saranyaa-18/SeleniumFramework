package learningselenium.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import learningselenium.testing.CartPage;
import learningselenium.testing.OrderPage;

public class AbstractComponent{
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement orderHeader;
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public void waitForElementToAppear(By elements)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(elements));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement findBy)
	{
		/*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
