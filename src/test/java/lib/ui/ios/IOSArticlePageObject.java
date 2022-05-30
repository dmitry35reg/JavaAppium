package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CREATED_FOLDER_ELEMENT_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_CLOSE_BUTTON = "id:Search";
    }

    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
