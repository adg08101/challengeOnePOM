package pageObject.searchTest;

import pageObject.hook.Hook;
import pageObject.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.hook.findBy;

public class GoogleSearchTests extends Hook {
    String expectedResult = "General Software Inc";
    String fakeSearchString = "General Software";
    String linkTag = "a";
    String gSoftPageURL = "https://www.gsoftinnovation.com/";

    @Test(priority=1)
    public void fakeSearchTest(){
    	//Search result is not what is expected
    	Assert.assertFalse(HomePage.doSearchAndCheckResult(fakeSearchString, expectedResult).getSearchResultValue());
    }
    
    @Test(priority=2)
    public void validSearchTest(){
    	//Search result meets what is expected
    	Assert.assertTrue(HomePage.doSearchAndCheckResult(expectedResult, expectedResult).getSearchResultValue());
    }
    
    @Test(priority=3)
    public void visitFirstResultSite(){
    	//Retrieve results
    	GoogleResultsPage results = HomePage.doSearchAndReturnResult(expectedResult, expectedResult);
    	//Visit first result site
    	util.getElementBy(findBy.tagName, linkTag, null, results.getResults().get(0)).click();
    }
    
    @Test(priority=4)
    public void checkResultURL(){
    	//Check for URL to be the expected one
    	Assert.assertTrue(util.getUrl(gSoftPageURL));
    }
    
    @Test(priority=5)
    public void gsoftPageTestSlide(){
    	//Check for URL to be GSoft (For singular testing purposes)
    	util.checkURLAndGet(gSoftPageURL);
    	//Test Slide
    	GsoftIncPage gSoftPage = new GsoftIncPage(getDriver(), getUtil());
    	Assert.assertTrue(gSoftPage.testSlide());
    }
    
    @Test(priority=6)
    public void gsoftPageTestContactForm(){
    	//Check for URL to be GSoft (For singular testing purposes)
    	util.checkURLAndGet(gSoftPageURL);
    	//Test Contact Form
    	GsoftIncPage gSoftPage = new GsoftIncPage(getDriver(), getUtil());
    	Assert.assertTrue(gSoftPage.testContactForm());
    }
    
    @Test(priority=7)
    public void gsoftPageTestContactFormFieldsFill(){
    	//Check for URL to be GSoft (For singular testing purposes)
    	util.checkURLAndGet(gSoftPageURL);
    	//Test Contact Form
    	GsoftIncPage gSoftPage = new GsoftIncPage(getDriver(), getUtil());
    	Assert.assertTrue(gSoftPage.testContactFormFill());
    }
}