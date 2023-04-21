package page;

import static constants.Constant.TimeoutVariable.WAIT_TIMEOUT_SECONDS;
import static constants.Constant.URLs.BASE_URL;
import static org.testng.Assert.assertEquals;

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

    public HomePage(WebDriver driver) {
        super(driver);
        driver.navigate().to(BASE_URL);
        log.info(String.format("Open '%s' page", BASE_URL));
        PageFactory.initElements(this.driver, this);
    }

    @Step("Input '{expression}' expression")
    public HomePage inputExpression(String expression) {
        expressionInput.clear();
        expressionInput.sendKeys(expression);
        log.info(String.format("Input '%s' expression", expression));
        return this;
    }

    @Step("Click Rad radio button")
    public HomePage clickRadRadioButton() {
        radRadioButton.click();
        log.info("Click Rad radio button");
        return this;
    }

    @Step("Execute calculation")
    public HomePage executeCalculation() {
        equalSignButton.click();
        log.info("Execute calculation");
        return this;
    }

    private HomePage waitForInputValueUpdate() {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.not(ExpectedConditions.attributeContains(expressionInput, "className", "loading")));
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("Expression input value wasn't updated");
        }
        return this;
    }

    public String getCalculationResult() {
        this.waitForInputValueUpdate();
        return expressionInput.getAttribute("value");
    }

    public List<String> getCalculationHistoryList() {
        historyDropdown.click();
        return historyRecordsList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Verify calculation result is: {expectedResult}")
    public void verifyCalculationResultIs(String expectedResult) {
        log.info(String.format("Verify calculation result is: %s", expectedResult));
        assertEquals(this.getCalculationResult(), expectedResult);
    }

    @Step("Verify calculation history is: {expectedResult}")
    public void verifyCalculationHistoryIs(List<String> expectedResult) {
        log.info(String.format("Verify calculation history is: %s", expectedResult));
        assertEquals(this.getCalculationHistoryList(), expectedResult);
    }
}
