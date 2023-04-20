package test;

import org.testng.annotations.Test;

import static constants.Constant.TestData.*;

public class CalculatorTest extends CommonConditions {

    @Test(description = "User should be able to see calculation history")
    public void shouldBeAbleToSeeCalculationHistory() {
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
