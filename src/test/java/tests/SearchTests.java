package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase
{
    @Test
    @Feature(value="Search")
    @DisplayName("Simple search article")
    @Description("Search for 'Java' and check search results")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Check cancel search")
    @Description("We init search and then cancel it")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Check amount of not empty search")
    @Description("Search the article and check that we have more than zero results")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Check amount of empty search")
    @Description("Search article without results and make sure that wikipedia doesn't return search results")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "dshsdfhsdf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Search by article and cancel search results")
    @Description("Search by the given text, get the results of the search and cancel the search results")
    @Step("Starting test testSearchAndCancel")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchAndCancel()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        int count = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "Articles count " + count + "<=1",
                count > 1
        );

        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Check the title and description of search results")
    @Description("We search by the text and check that every search result contains correct title and description")
    @Step("Starting test testSearchTitleAndDesc")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchTitleAndDesc()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java","sland in Indonesia, Southeast Asia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript","igh-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)","bject-oriented programming language");
    }
}
