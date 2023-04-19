package page;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import waits.CustomConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {

    private final String BASE_URL = "https://web2.0calc.com/";

    @FindBy(xpath = "//button[@value='consent']")
    private WebElement managementPlatformLoadingModalConsentButton;

    @FindBy(xpath = "//button[@aria-label='Consent']")
    private WebElement personalDataUsageModalConsentButton;

    @FindBy(id = "input")
    private WebElement expressionInput;

    @FindBy(id = "trigorad")
    private WebElement radRadioButton;

    @FindBy(id = "BtnCalc")
    private WebElement equalSignButton;

    @FindBy(id = "hist")
    private WebElement historyDropdown;

    @FindBy(xpath = "//p[@data-inp]")
    List<WebElement> historyRecordsList;

    private String expression;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Open Home Page")
    @Override
    public HomePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    @Step("Wait for Home Page load")
    public HomePage waitForPageLoad() {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jQueryAJAXsCompleted());
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("Home page hasn't been loaded.");
        }
        return this;
    }

    @Step("Wait for Management Platform Loading Modal load")
    public HomePage waitForManagementPlatformLoadingModalLoad() {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(managementPlatformLoadingModalConsentButton));
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("Management Platform Loading Modal hasn't been loaded.");
        }
        return this;
    }

    @Step("Click Management Platform Loading Modal Consent button")
    public HomePage clickManagementPlatformLoadingModalConsentButton() {
        managementPlatformLoadingModalConsentButton.click();
        return this;
    }

    @Step("Wait for Personal Data Usage Modal load")
    public HomePage waitForPersonalDataUsageModalLoad() {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(personalDataUsageModalConsentButton));
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("MPersonal Data Usage Modal hasn't been loaded.");
        }
        return this;
    }

    @Step("Click Personal Data Usage Modal Consent button")
    public HomePage clickPersonalDataUsageModalConsentButton() {
        personalDataUsageModalConsentButton.click();
        return this;
    }

    @Step("Input '{expression}' expression")
    public HomePage inputExpression(String expression) {
        this.expression = expression;
        expressionInput.sendKeys(expression);
        return this;
    }

    @Step("Click Rad radio button")
    public HomePage clickRadRadioButton() {
        radRadioButton.click();
        return this;
    }

    @Step("Click Equal Sign button")
    public HomePage clickEqualSignButton() {
        equalSignButton.click();
        return this;
    }

    @Step("Wait for Expression Input value update")
    public HomePage waitForExpressionInputValueUpdate() {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(expressionInput, expression)));
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("Expression input value wasn't updated");
        }
        return this;
    }

    @Step("Get calculation result")
    public String getCalculationResult() {
        return expressionInput.getAttribute("value");
    }

    @Step("Clear Expression input")
    public HomePage clearExpressionInput() {
        expressionInput.clear();
        return this;
    }

    @Step("Click History dropdown")
    public HomePage clickHistoryDropdown() {
        historyDropdown.click();
        return this;
    }

    @Step("Get Calculation History list")
    public List<String> getCalculationHistoryList() {
        return historyRecordsList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
