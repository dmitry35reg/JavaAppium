package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase
{

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Change screen orientation on search result")
    @Description("We open 'Java object-oriented programming language' article, rotate screen landscape and make sure the title is expected")
    @Step("Starting test testChangeScreenOrientationOnSearchResult")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResult()
    {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Background app on search page")
    @Description("We open 'Java object-oriented programming language' article, background app for 2 sec and make sure the title is expected")
    @Step("Starting test testSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchArticleInBackground()
    {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
