package pageObject.hook;

import java.util.ArrayList;
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
        setWaitSeconds(5);
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
    
    public boolean fillFormByArrays(String[] xpaths, String[] values, WebDriver driver, String submitBtn, 
    		String cancelBtn, boolean fakeIt) {
    	try {
	    	if (xpaths.length == values.length) {
		    	List<List<String>> data = new ArrayList<List<String>>();
				for (int i =0; i < 2; i++)
		            data.add(new ArrayList<String>());
				for (int i = 0;i < xpaths.length;i++) {
					data.get(0).add(xpaths[i]);
					data.get(1).add(values[i]);
				}
				for (int i = 0;i < xpaths.length;i++) {
					getElementBy(findBy.xpath, data.get(0).get(i), driver, null).sendKeys(data.get(1).get(i));
				}
				if (!fakeIt)
					getElementBy(findBy.xpath, submitBtn, driver, null).click();
				else
					getElementBy(findBy.xpath, cancelBtn, driver, null).click();
	    	} else {
	    		return false;
	    	}
	    	return true;
    	} catch(Exception e) {
    		driver.navigate().refresh();
    		return true;
    	}
    }

	public void clickableToAction(String testXpath, String xpath) {
		try {
			getElementBy(findBy.xpath, testXpath, driver, null).click();
		} catch(Exception e) {
			getElementBy(findBy.xpath, xpath, driver, null).click();
		}
	}
}