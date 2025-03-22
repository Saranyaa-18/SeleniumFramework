package learningselenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import learningselenium.testing.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest{

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeWebDriver() throws IOException{
		
		System.out.println("yes");
		Properties prop = new Properties();
		//E:\Eclipse Java Workspace\SeleniumFramework\src\main\java\learningselenium\resources\GlobalData.properties
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\learningselenium\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") :	prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{		
			System.setProperty("webdriver.gecko.driver", "c:\\Users\\Admin\\Documents\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{		
			System.setProperty("webdrive.edge.driver", "c:\\Users\\Admin\\Documents\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException
	{
		String jsonContent = 	FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage getUrl() throws IOException
	{
		driver = initializeWebDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.close();
	}
	
}


