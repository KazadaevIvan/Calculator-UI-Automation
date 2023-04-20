package test;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static constants.Constant.TestData.*;

public class CalculatorTest extends CommonConditions {

    @Test
    public void shouldBeAbleToSeePerformedCalculationHistory() {
        List<String> expressionsList = Arrays.asList(firstExpression, secondExpression, thirdExpression);

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
