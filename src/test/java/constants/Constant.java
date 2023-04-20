package constants;

import org.openqa.selenium.Cookie;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static class TimeoutVariable {
        public static final int WAIT_TIMEOUT_SECONDS = 10;
    }

    public static class URLs {
        public static final String BASE_URL = "https://web2.0calc.com/";
    }

    public static class TestData {
        public static final String firstExpression = "35*999+(100/4)";
        public static final String expectedFirstCalculationResult = "34990";
        public static final String secondExpression = "cos(pi)";
        public static final String expectedSecondCalculationResult = "-1";
        public static final String thirdExpression = "sqrt(81)";
        public static final String expectedThirdCalculationResult = "9";
        public static final List<String> expectedCalculationHistory = Arrays.asList(thirdExpression, secondExpression, firstExpression);
    }

    public static class Cookies {
        public static final Cookie consentManagementPlatformLoadingCookie = new Cookie("precmp", "1");
        public static final Cookie consentPersonalDataUsageCookie = new Cookie("FCCDCF", "%5Bnull%2Cnull%2Cnull%2C%5B%22CPqd9YAPqd9YAEsABBENDACoAP_AAG_AAAiQINJB7D7FbSFCyP57aLsAMAhXRkCAQqQCAASBAmABQAKQIAQCkkAYFESgBAACAAAgICJBIQIMCAgACUABQAAAAAEEAAAABAAIIAAAgAEAAAAIAAACAIAAEAAIAAAAEAAAmQhAAIIACAAAhAAAIAAAAAAAAAAAAgCAAAAAAAAAAAAAAAAAAQQaQD2F2K2kKEkfjWUWYAQBCujIEAhUAEAAECBIAAAAUgQAgFIIAwAIlACAAAAABAQEQCQgAQABAAAoACgAAAAAAAAAAAAAAQQAABAAIAAAAAAAAEAQAAIAAQAAAAAAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAAgAA%22%2C%221~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.317.323.2373.338.358.2415.415.2506.2526.482.486.494.495.2568.2571.2575.540.574.2624.2677.827.864.981.1048.1051.1095.1097.1171.1201.1205.1276.1301.1365.1415.1449.1570.1577.1651.1716.1753.1765.1870.1878.1889.1958%22%2C%22986B0C6A-DE6D-42BA-B128-B66CE1472F2C%22%5D%2Cnull%2Cnull%2C%5B%5D%5D");
    }
}
