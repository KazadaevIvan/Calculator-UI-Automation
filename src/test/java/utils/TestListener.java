package utils;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener, TestLifecycleListener {
    private Logger log = LogManager.getRootLogger();

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            AllureUtils.takeScreenshot();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("================= STARTING TEST %s =================%n", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("================= FINISHED TEST %s Duration: %ss =================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("================= FAILED TEST %s Duration: %ss =================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("================= SKIPPING TEST %s =================%n", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
