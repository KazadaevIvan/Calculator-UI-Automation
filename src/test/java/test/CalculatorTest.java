package test;

import org.testng.annotations.Test;

import static constants.Constant.TestData.*;

public class CalculatorTest extends CommonConditions {

    @Test
    public void shouldBeAbleToSeePerformedCalculationHistory() {
        homePage
                .inputExpression(firstExpression)
                .executeCalculation()
                .verifyCalculationResultIs(expectedFirstCalculationResult);

        homePage
                .inputExpression(secondExpression)
                .clickRadRadioButton()
                .executeCalculation()
                .verifyCalculationResultIs(expectedSecondCalculationResult);

        homePage
                .inputExpression(thirdExpression)
                .executeCalculation()
                .verifyCalculationResultIs(expectedThirdCalculationResult);

        homePage.verifyCalculationHistoryIs(expectedCalculationHistory);
    }
}
