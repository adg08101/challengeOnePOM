package pageObject.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.hook.Hook;
import pageObject.hook.Utility;
import pageObject.hook.findBy;

public class GoogleSearchPage extends Hook {
    String searchInput = "//input[@name='q']";
    String searchResults = "div[class='g']";
    GoogleResultsPage resultsPage;
    List<WebElement> results;

	public GoogleSearchPage(WebDriver remoteDriver, Utility remoteUtil){
        driver = remoteDriver;
        util = remoteUtil;
        resultsPage = new GoogleResultsPage(driver, util);
    }

    public List<WebElement> getResults() {
		return results;
	}

	public void setResults(List<WebElement> results) {
		this.results = results;
	}

    public GoogleResultsPage doSearchAndCheckResult(String queryString, String expectedStr) {  	
        WebElement query = util.getElementBy(findBy.xpath, searchInput, driver, null);
        query.clear();
        query.sendKeys(queryString);
        query.sendKeys(Keys.RETURN);
        
        setResults(util.getElementsBy(findBy.cssSelector, searchResults, driver));

        resultsPage.checkResult(getResults(), expectedStr);
        return resultsPage;
    }
    
    public GoogleResultsPage doSearchAndReturnResult(String queryString, String expectedStr) {
    	setResults(util.getElementsBy(findBy.cssSelector, searchResults, driver));
    	
        resultsPage.setResults(getResults());
        return resultsPage;
    }
}