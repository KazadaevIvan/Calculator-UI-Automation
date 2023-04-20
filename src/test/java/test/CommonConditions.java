package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import page.HomePage;
import utils.TestListener;

import static constants.Constant.Cookies.consentManagementPlatformLoadingCookie;
import static constants.Constant.Cookies.consentPersonalDataUsageCookie;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverSingleton.getDriver();
        homePage = new HomePage(driver);
        driver.manage().addCookie(consentManagementPlatformLoadingCookie);
        driver.manage().addCookie(consentPersonalDataUsageCookie);
        driver.navigate().refresh();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
