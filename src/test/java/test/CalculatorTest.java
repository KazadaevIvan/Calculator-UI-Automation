package test;


import org.testng.annotations.Test;
import steps.HomePageSteps;

import java.util.Arrays;
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

        HomePageSteps homePageSteps = new HomePageSteps(driver)
                .openPage()
                .consentManagementPlatformLoadingAndPersonalDataUsage();

        homePageSteps
                .inputExpression(firstExpression)
                .executeCalculation()
                .verifyCalculationResultIs(expectedFirstCalculationResult);

        homePageSteps
                .inputExpression(secondExpression)
                .clickRadRadioButton()
                .executeCalculation()
                .verifyCalculationResultIs(expectedSecondCalculationResult);

        homePageSteps
                .inputExpression(thirdExpression)
                .executeCalculation()
                .verifyCalculationResultIs(expectedThirdCalculationResult);

        homePageSteps.verifyCalculationHistoryIs(expressionsList);
    }
}
