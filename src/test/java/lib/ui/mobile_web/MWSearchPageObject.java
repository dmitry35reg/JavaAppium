package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type ='search']";
        SEARCH_CANCEL_BUTTON = "xpath://div[3]/div/div[1]/div/div[2]/button";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://li[contains(@class,'page-summary')][@title='{TITLE}']//div[@class='wikidata-description'][contains(text(),'{DESCRIPTION}')]";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
