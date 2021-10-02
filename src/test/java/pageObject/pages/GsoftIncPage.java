package pageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.hook.Hook;
import pageObject.hook.Utility;
import pageObject.hook.findBy;

public class GsoftIncPage extends Hook {
    String slideBtn = "/html/body/div/div[1]/div[3]/div[1]/a[2]";
    String contactBtn = "/html/body/div/div[1]/div[1]/div[1]/button";

	public GsoftIncPage(WebDriver remoteDriver, Utility remoteUtil){
        driver = remoteDriver;
        util = remoteUtil;
    }

    public boolean testSlide() {
    	//Test slide code
    	try {
    		if (util.getMyWaitVar().until(ExpectedConditions.elementToBeClickable(
    				util.getElementBy(findBy.xpath, slideBtn, driver, null))) != null) {
		    	util.getElementBy(findBy.xpath, slideBtn, driver, null).click();
    		}
    	} catch (Exception e) {
    		System.out.println("An Error occurred: " + e.getMessage());
    		return false;
    	}
    	return true; 
    }

	public boolean testContactForm() {
		util.getElementBy(findBy.xpath, contactBtn, driver, null).click();
		return true;
	}
}