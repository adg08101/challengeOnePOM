package pageObject.hook;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.beust.jcommander.internal.Nullable;

public class Utility {

    WebDriver driver;
    WebDriverWait myWaitVar;
    int waitSeconds;

    public Utility(WebDriver remoteDriver){
        driver = remoteDriver;
        setWaitSeconds(15);
        myWaitVar = new WebDriverWait(driver, getWaitSeconds());
    }

    public int getWaitSeconds() {
		return waitSeconds;
	}

	public void setWaitSeconds(int waitSeconds) {
		this.waitSeconds = waitSeconds;
	}

	public WebDriverWait getMyWaitVar() {
		return myWaitVar;
	}

	public void setMyWaitVar(WebDriverWait myWaitVar) {
		this.myWaitVar = myWaitVar;
	}

	public boolean getTitle(String titleText){
        String title = driver.getTitle();
        boolean findText = title.contains(titleText);
        return findText;
    }

    public boolean getUrl(String urlLoginExpected){
        String Url = driver.getCurrentUrl();
        boolean findUrl = Url.contains(urlLoginExpected);
        return findUrl;
    }
    
    public boolean doesWebElementTextContainsCriteria(WebElement element, String search) {
    	return element.getText().contains(search);
    }
    
    public WebElement getElementBy(findBy by, String searchString, @Nullable WebDriver driver, 
    		@Nullable WebElement element) {
    	if (driver != null) {
    		switch (by) {
    		case xpath:
    			return driver.findElement(By.xpath(searchString));
    		case tagName:
    			return driver.findElement(By.tagName(searchString));
    		default:
    			return null;
    		}
    	} else {
    		switch (by) {
    		case xpath:
    			return element.findElement(By.xpath(searchString));
    		case tagName:
    			return element.findElement(By.tagName(searchString));
    		default:
    			return null;
    		}
    	}
    }
    
    public List<WebElement> getElementsBy(findBy by, String searchString, WebDriver driver) {
    	switch (by) {
		case cssSelector:
			return driver.findElements(By.cssSelector(searchString));
		default:
			return null;
		}
    }
    
    public void checkURLAndGet(String url) {
    	if (!getUrl(url))
    		driver.get(url);
    }
}