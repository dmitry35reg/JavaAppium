package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject
{
        static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SYNC_SAVED_ARTICLES_POP_UP_CLOSE = "id:Close";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver)
        {
            super(driver);
        }
}
