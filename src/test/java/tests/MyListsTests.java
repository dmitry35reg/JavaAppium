package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for My Lists")
public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Dmitriy35reg",
            password = "rafoleloge78";

    @Test
    @Features(value = {@Feature(value = "My Lists"), @Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Save the first article to my lists")
    @Description("We find 'Java object-oriented programming language' article and save it to My Lists")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }


        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
            SearchPageObject.waitForCancelButtonToDisappear();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeSyncSavedArticlesPopUp();
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
    @Features(value = {@Feature(value = "My Lists"), @Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Save and delete articles to my lists")
    @Description("We find two articles and save them to My Lists, after that delete one of articles and check another article is still present")
    @Step("Starting test testSaveTwoArticlesToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isMw())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            String article_title = "Java (programming language)";
            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring("Programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        }

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToCreatedFolderByName(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeSyncSavedArticlesPopUp();
        }

        String first_article_title = "Java (programming language)";
        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        String second_article_title = "JavaScript";
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);
    }
}
