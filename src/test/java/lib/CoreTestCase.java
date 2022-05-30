package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";


    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown()
    {
        driver.quit();
    }



    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else{
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else{
            System.out.println("Method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background (This method does nothing for Mobile Web)")
    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else{
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open Wikipedia URL for Mobile Web (This method does nothing for Android and iOS)")
    protected void openWikiPageForMobileWeb()
    {
        if (Platform.getInstance().isMw()){
            driver.get("http://en.m.wikipedia.org/");
        } else{
            System.out.println("Method openWikiPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page screen for iOS (This method does nothing for Android and Mobile Web)")
    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    @Step("Creating allure property file")
    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
