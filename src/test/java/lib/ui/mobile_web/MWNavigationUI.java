package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        LOGIN_NAVIGATION = "xpath://*[@id=\"p-personal\"]/li/a/span[2]";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
