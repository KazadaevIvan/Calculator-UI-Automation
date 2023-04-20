package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.HomePageSteps;
import utils.TestListener;

import static constants.Constant.Cookies.consentManagementPlatformLoadingCookie;
import static constants.Constant.Cookies.consentPersonalDataUsageCookie;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected HomePageSteps homePageSteps;

    @BeforeMethod
    public void setUp() {
        driver = DriverSingleton.getDriver();
        homePageSteps = new HomePageSteps(driver);
        driver.manage().addCookie(consentManagementPlatformLoadingCookie);
        driver.manage().addCookie(consentPersonalDataUsageCookie);
        driver.navigate().refresh();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
