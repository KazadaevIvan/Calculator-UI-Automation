package page;

import static constants.Constant.TimeoutVariable.WAIT_TIMEOUT_SECONDS;
import static constants.Constant.URLs.BASE_URL;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page.enums.*;
import page.enums.Number;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {
    @FindBy(id = "input")
    private WebElement expressionInput;

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

    @Step("Click number '{number}' button")
    public HomePage clickNumber(Number number) {
        findElementByIdAndClick(number);
        log.info(String.format("Click number '%s' button", number));
        return this;
    }

    @Step("Click operator '{operator}' button")
    public HomePage clickOperator(Operator operator) {
        findElementByIdAndClick(operator);
        log.info(String.format("Click operator '%s' button", operator));
        return this;
    }

    @Step("Click bracket '{bracket}' button")
    public HomePage clickBracket(Bracket bracket) {
        findElementByIdAndClick(bracket);
        log.info(String.format("Click bracket '%s' button", bracket));
        return this;
    }

    @Step("Click function '{function}' button")
    public HomePage clickFunction(Function function) {
        findElementByIdAndClick(function);
        log.info(String.format("Click function '%s' button", function));
        return this;
    }

    @Step("Click constant '{constant}' button")
    public HomePage clickConstant(Constant constant) {
        findElementByIdAndClick(constant);
        log.info(String.format("Click constant '%s' button", constant));
        return this;
    }

    @Step("Click angle mode '{angleMode}' button")
    public HomePage clickAngleMode(AngleMode angleMode) {
        findElementByIdAndClick(angleMode);
        log.info(String.format("Click angle mode '%s' button", angleMode));
        return this;
    }

    @Step("Click power '{power}' button")
    public HomePage clickPower(Power power) {
        findElementByIdAndClick(power);
        log.info(String.format("Click power '%s' button", power));
        return this;
    }

    private HomePage findElementByIdAndClick(IEnum id) {
        driver.findElement(By.id(id.getValue())).click();
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
}
