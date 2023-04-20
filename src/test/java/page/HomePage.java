package page;

import static constants.Constant.TimeoutVariable.WAIT_TIMEOUT_SECONDS;
import static constants.Constant.URLs.BASE_URL;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {
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
        driver.navigate().to(BASE_URL);
        PageFactory.initElements(this.driver, this);
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
