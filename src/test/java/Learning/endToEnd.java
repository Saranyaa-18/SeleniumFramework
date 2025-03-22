package Learning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class endToEnd {
	
	//@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String [] originalValue = {"ZARA COAT 3","IPHONE 13 PRO"};
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("raju.saranyaa18@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("DancingDoll@18");
		driver.findElement(By.id("login")).click();
		List<WebElement> productlist = driver.findElements(By.cssSelector(".offset-md-0"));
		WebElement prod = productlist.stream().filter(product->product.findElement(By.xpath("//h5[contains(@style,'text-transform')]/b")).getText().equals(originalValue[0])).findFirst().orElse(null);
		prod.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));	
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();	
		List <WebElement> cartLists = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartLists.stream().anyMatch(cartList->cartList.getText().equalsIgnoreCase(originalValue[0]));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		List<WebElement> countryList = driver.findElements(By.cssSelector(".list-group span[class='ng-star-inserted']"));
		countryList.stream().filter(list->list.getText().equals("India")).findFirst().orElse(null).click();
		driver.findElement(By.cssSelector(".btnn")).click();
		String conformationText = driver.findElement(By.className("hero-primary")).getText();		
		Assert.assertEquals(conformationText, "THANKYOU FOR THE ORDER.");
		driver.close();
		
		
		
		
		/*int j=0;
		for(int i=0;i<productlist.size();i++)
		{
			//driver.findElements(By.xpath("//h5[contains(@style,'text-transform')]/b")).get(i).getText();
			String comparedValue = driver.findElements(By.xpath("//h5[contains(@style,'text-transform')]/b")).get(i).getText();
			if(originalValue[j].equals(comparedValue))
			{
				driver.findElements(By.xpath("//button[text()=' Add To Cart']")).get(i).click();
			j++;
			Thread.sleep(5000);
			}
		}*/
		
	}

}
