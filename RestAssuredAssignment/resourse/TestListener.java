

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener{
    
	@Override
    public void onTestStart(ITestResult result) {
        Reporter.log("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test Skipped: " + result.getName());
    }


    @Override
    public void onStart(ITestContext context) {
    	Reporter.log("Execution Started");
    }

    @Override
    public void onFinish(ITestContext context) {
    	Reporter.log("Execution ended");
    }
}
