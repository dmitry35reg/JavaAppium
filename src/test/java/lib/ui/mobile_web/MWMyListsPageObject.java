package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(@text,'{TITLE}')]";
        REMOVED_FROM_SAVED_BUTTON_TPL ="xpath://ul[contains(@class,'watchlist')]//h3[contains(@text,'{TITLE}')]/../../div[contains(@class,''watched)]";
        SYNC_SAVED_ARTICLES_POP_UP_CLOSE = "id:Close";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
