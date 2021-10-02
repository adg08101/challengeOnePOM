package pageObject.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.hook.Hook;
import pageObject.hook.Utility;

public class GoogleResultsPage extends Hook {
    private boolean searchResultValue = false;
    private List<WebElement> results = null;

    public GoogleResultsPage(WebDriver remoteDriver, Utility remoteUtil){
        driver = remoteDriver;
        util = remoteUtil;
    }

    public void checkResult(List<WebElement> searchResult, String expectedStr) {
    	setResults(searchResult);
        for (int i = 0;i < searchResult.size();i++) {
      		if (util.doesWebElementTextContainsCriteria(searchResult.get(i), expectedStr)) {
      			setSearchResultValue(true);
      			break;
      		}
    	}
    }

	public boolean getSearchResultValue() {
		return searchResultValue;
	}

	void setSearchResultValue(boolean searchResultValue) {
		this.searchResultValue = searchResultValue;
	}

	public List<WebElement> getResults() {
		return results;
	}

	void setResults(List<WebElement> results) {
		this.results = results;
	}
}