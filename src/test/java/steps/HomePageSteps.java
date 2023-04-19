package steps;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.HomePage;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HomePageSteps {
    private HomePage homePage;
    private Logger log;

    public  HomePageSteps(WebDriver driver) {
        this.homePage = new HomePage(driver);
        log = LogManager.getRootLogger();
    }

    @Step("Open Home Page")
    public HomePageSteps openPage() {
        log.info("Open Home Page");
        homePage
                .openPage()
                .waitForPageLoad();
        return this;
    }

    @Step("Consent Management Platform Loading and Personal Data Usage")
    public HomePageSteps consentManagementPlatformLoadingAndPersonalDataUsage() {
        log.info("Consent Management Platform Loading and Personal Data Usage");
        homePage
                .waitForManagementPlatformLoadingModalLoad()
                .clickManagementPlatformLoadingModalConsentButton()
                .waitForPersonalDataUsageModalLoad()
                .clickPersonalDataUsageModalConsentButton();
        return this;
    }

    @Step("Input '{expression}' expression")
    public HomePageSteps inputExpression(String expression) {
        log.info(String.format("Input '%s' expression", expression));
        homePage
                .clearExpressionInput()
                .inputExpression(expression);
        return this;
    }

    @Step("Click Rad radio button")
    public HomePageSteps clickRadRadioButton() {
        log.info("Click Rad radio button");
        homePage
                .clickRadRadioButton();
        return this;
    }

    @Step("Execute Calculation")
    public HomePageSteps executeCalculation() {
        log.info("Execute Calculation");
        homePage
                .clickEqualSignButton()
                .waitForExpressionInputValueUpdate();
        return this;
    }

    @Step("Verify calculation result is: {expectedResult}")
    public void verifyCalculationResultIs(String expectedResult) {
        log.info(String.format("Verify calculation result is: %s", expectedResult));
        assertEquals(homePage.getCalculationResult(), expectedResult);
    }

    @Step("Verify Calculation History is: {expectedResult}")
    public void verifyCalculationHistoryIs(List<String> expectedResult) {
        log.info(String.format("Verify Calculation History is: %s", expectedResult));
        List<String> calculationHistoryList = homePage
                .clickHistoryDropdown()
                .getCalculationHistoryList();
        Collections.reverse(calculationHistoryList);

        assertEquals(calculationHistoryList, expectedResult);
    }
}
