package learningselenium.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningselenium.AbstractComponent.AbstractComponent;

public class OrderConfomationPage extends AbstractComponent{

WebDriver driver;
	
	public OrderConfomationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement conformationText;
	
	public String getConformationText()
	{
		return conformationText.getText();
	}
}
