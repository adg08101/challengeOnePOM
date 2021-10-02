package pageObject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.hook.Hook;
import pageObject.hook.Utility;
import pageObject.hook.findBy;

public class GsoftIncPage extends Hook {
    String slideBtn = "/html/body/div/div[1]/div[3]/div[1]/a[2]";
    String contactBtn = "/html/body/div/div[1]/div[1]/div[1]/button";
    String nameXpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/form/div[2]/input";
    String emailXpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/form/div[3]/input";
    String businessXpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/form/div[4]/input";
    String bodyXpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/form/div[5]/textarea";
    String submitBtnXpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/form/button";
    String canceBtnXpath = "/html/body/div[1]/div[1]/div[3]/div/div/span/svg/path";

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
		try {
			util.getElementBy(findBy.xpath, contactBtn, driver, null).click();
		} catch (Exception e) {
    		System.out.println("An Error occurred: " + e.getMessage());
    		return false;
    	}
		return true;
	}

	public boolean testContactFormFill() {
		String[] xpaths = {nameXpath, emailXpath, businessXpath, bodyXpath};
		String[] values = {"Ahmed Davila", "ahmed.davila@generalsoftwareinc.com", "Personal Home", 
				"Testing Contact Info Submit"};
		util.clickableToAction(nameXpath, contactBtn);
		util.fillFormByArrays(xpaths, values, driver, submitBtnXpath, canceBtnXpath, true);
		return true;
	}
}