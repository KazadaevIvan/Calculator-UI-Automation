package test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static page.enums.AngleMode.*;
import static page.enums.Function.*;
import static constants.Constant.TestData.*;
import static page.enums.Bracket.*;
import static page.enums.Number.*;
import static page.enums.Operator.*;
import static page.enums.Constant.*;
import static page.enums.Power.*;

public class CalculatorTest extends CommonConditions {

    @Test(description = "User should be able to see calculation history")
    public void shouldBeAbleToSeeCalculationHistory() {
        String actualFirstCalculationResult = homePage
                .clickNumber(THREE).clickNumber(FIVE)
                .clickOperator(MULTIPLY)
                .clickNumber(NINE).clickNumber(NINE).clickNumber(NINE)
                .clickOperator(ADD)
                .clickBracket(ROUND_BRACKET_LEFT)
                .clickNumber(ONE).clickNumber(ZERO).clickNumber(ZERO)
                .clickOperator(DIVIDE)
                .clickNumber(FOUR)
                .clickBracket(ROUND_BRACKET_RIGHT)
                .clickOperator(EQUAL)
                .getCalculationResult();

        assertEquals(actualFirstCalculationResult, expectedFirstCalculationResult);

        String actualSecondCalculationResult = homePage
                .clickOperator(CLEAR)
                .clickFunction(COS)
                .clickConstant(PI)
                .clickBracket(ROUND_BRACKET_RIGHT)
                .clickAngleMode(RAD)
                .clickOperator(EQUAL)
                .getCalculationResult();

        assertEquals(actualSecondCalculationResult, expectedSecondCalculationResult);

        String actualThirdCalculationResult = homePage
                .clickOperator(CLEAR)
                .clickPower(SQRT)
                .clickNumber(EIGHT).clickNumber(ONE)
                .clickBracket(ROUND_BRACKET_RIGHT)
                .clickOperator(EQUAL)
                .getCalculationResult();

        assertEquals(actualThirdCalculationResult, expectedThirdCalculationResult);

        assertEquals(homePage.getCalculationHistoryList(), expectedCalculationHistory);
    }
}
