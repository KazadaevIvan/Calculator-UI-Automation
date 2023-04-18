package test;

import org.testng.annotations.Test;
import page.HomePage;

public class CalculatorTest extends CommonConditions {

    @Test
    public void shouldBeAbleToSeePerformedCalculationHistory() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
    }
}
