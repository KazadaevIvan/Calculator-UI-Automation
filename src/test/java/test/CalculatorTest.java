package test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import page.HomePage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalculatorTest extends CommonConditions {

    @Test
    public void shouldBeAbleToSeePerformedCalculationHistory() {
        String firstExpression = "35*999+(100/4)";
        String secondExpression = "cos(pi)";
        String thirdExpression = "sqrt(81)";

        List<String> expressionsList = Arrays.asList(firstExpression, secondExpression, thirdExpression);

        String expectedFirstCalculationResult = "34990";
        String expectedSecondCalculationResult = "-1";
        String expectedThirdCalculationResult = "9";

        HomePage homePage = new HomePage(driver);

        String firstCalculationResult = homePage
                .openPage()
                .waitForPageLoad()
                .consentManagementPlatformLoading()
                .consentPersonalDataUsage()
                .inputExpression(firstExpression)
                .clickEqualSignButton()
                .waitForExpressionInputValueUpdate()
                .getCalculationResult();
        assertEquals(firstCalculationResult, expectedFirstCalculationResult);

        String secondCalculationResult = homePage
                .clearExpressionInput()
                .inputExpression(secondExpression)
                .clickRadRadioButton()
                .clickEqualSignButton()
                .waitForExpressionInputValueUpdate()
                .getCalculationResult();
        assertEquals(secondCalculationResult, expectedSecondCalculationResult);

        String thirdCalculationResult = homePage
                .clearExpressionInput()
                .inputExpression(thirdExpression)
                .clickEqualSignButton()
                .waitForExpressionInputValueUpdate()
                .getCalculationResult();
        assertEquals(thirdCalculationResult, expectedThirdCalculationResult);

        List<String> calculationHistoryList = homePage
                .clickHistoryDropdown()
                .getCalculationHistoryList();
        Collections.reverse(calculationHistoryList);

        assertEquals(calculationHistoryList, expressionsList);
    }
}
