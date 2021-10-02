package pageObject.hook;

import pageObject.pages.GoogleSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

public class Hook {
	public WebDriver driver;
    public pageObject.pages.GoogleSearchPage HomePage;
    public Utility util;
    public String startURL = "https://google.com.cu";

    @BeforeTest
    public void setUp(){
    	try {
	        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	        setDriver(new ChromeDriver());
	        setUtil(new Utility(getDriver()));
	        
	        driver.get(startURL);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        HomePage = new GoogleSearchPage(getDriver(), getUtil());
    	} catch (Exception e) {
    		System.out.println("An Error occurred: " + e.getMessage());
    	}
    }

    public Utility getUtil() {
		return util;
	}

	public void setUtil(Utility util) {
		this.util = util;
	}

	@AfterTest
    public void close(){
    	System.out.println("Test completed!!");
        driver.close();
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}