package learningselenium.testing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import learningselenium.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css=".offset-md-0")
	List <WebElement> productlist;
	
	@FindBy(css=".ng-animating")
	WebElement addToCartAnimationDisappaer;
	
	By productsBy = By.cssSelector(".offset-md-0");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By addToCartAppearWait = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return productlist;
	}
	public WebElement getProductName(String originalValue)
	{
		WebElement productName = getProductList().stream().filter(product->product.findElement
				(By.cssSelector("b")).getText().equals(originalValue)).findFirst().orElse(null);
		System.out.println(productName.getText());
		return productName;
	}
	
	public void addProductToCart(String originalValue)
	{
		WebElement prod = getProductName(originalValue);
		prod.findElement(addToCart).click();
		waitForElementToAppear(addToCartAppearWait);
		waitForElementToDisappear(addToCartAnimationDisappaer);
	}
}
