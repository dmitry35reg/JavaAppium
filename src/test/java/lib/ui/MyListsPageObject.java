package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVED_FROM_SAVED_BUTTON_TPL,
            SYNC_SAVED_ARTICLES_POP_UP_CLOSE;

    public MyListsPageObject (RemoteWebDriver driver)
    {
        super (driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return REMOVED_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /*TEMPLATES METHODS*/

    public void closeSyncSavedArticlesPopUp() {
        this.waitForElementAndClick(SYNC_SAVED_ARTICLES_POP_UP_CLOSE, "Cannot find and click close pop up button", 5);
    }

    public void openFolderByName(String name_of_folder){
        String folderNameXpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String articleXpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent((articleXpath), "Cannot find saved article by title " + article_title, 15);
    }

    public void openArticleByTitle(String article_title) {
        String articleXpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick((articleXpath), "Cannot find saved article by title " + article_title, 5);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String articleXpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent((articleXpath), "Saved article is still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String articleXpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
        );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
