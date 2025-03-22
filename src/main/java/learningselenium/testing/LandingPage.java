package learningselenium.testing;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningselenium.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmails;
	
	@FindBy(id="userPassword")
	WebElement userPasswords;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='toast-error']")
	WebElement incorrectLoginDetails;
	
	public ProductCatalogue loginDetails(String email, String password)
	{
		userEmails.sendKeys(email);
		userPasswords.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String loginErrorValidation()
	{
		waitForWebElementToAppear(incorrectLoginDetails);
		return incorrectLoginDetails.getText();
	}
	
	public void goTo()
	{	
		driver.get("https://rahulshettyacademy.com/client/");
	}

}


