package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {

    private final String BASE_URL = "https://web2.0calc.com/";

    @FindBy(xpath = "//button[@value='consent']")
    private WebElement consentButton;

    @FindBy(xpath = "//button[@aria-label='Consent']")
    private WebElement consentButton2;

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

    @Override
    public HomePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public HomePage waitForPageLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public HomePage consentManagementPlatformLoading() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(consentButton));
        consentButton.click();
        return this;
    }

    public HomePage consentPersonalDataUsage() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(consentButton2));
        consentButton2.click();
        return this;
    }

    public HomePage inputExpression(String expression) {
        this.expression = expression;
        expressionInput.sendKeys(expression);
        return this;
    }

    public HomePage clickRadRadioButton() {
        radRadioButton.click();
        return this;
    }

    public HomePage clickEqualSignButton() {
        equalSignButton.click();
        return this;
    }

    public HomePage waitForExpressionInputValueUpdate() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(expressionInput, expression)));
        return this;
    }

    public String getCalculationResult() {
        return expressionInput.getAttribute("value");
    }

    public HomePage clearExpressionInput() {
        expressionInput.clear();
        return this;
    }

    public HomePage clickHistoryDropdown() {
        historyDropdown.click();
        return this;
    }

    public List<String> getCalculationHistoryList() {
        return historyRecordsList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
