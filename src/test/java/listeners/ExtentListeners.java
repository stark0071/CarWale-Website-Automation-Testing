package listeners;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;

import base.BaseTest;

public class ExtentListeners implements ITestListener {

    static Date d = new Date();
    static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

    private static ExtentReports extent = ExtentManager.createInstance(
            System.getProperty("user.dir") + "\\target\\reports\\" + fileName);

    public static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
    	String param =  (String)result.getParameters()[0];
        test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName()+" -- "+param);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        test.pass(m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Access current test class instance and call its captureScreenshot()
        BaseTest base = (BaseTest) result.getInstance();
        String screenshot = base.captureScreenshot(); // driver is used internally here

        System.setProperty("org.uncommons.reportng.escape-output", "false");

        // ReportNG screenshot
        Reporter.log("<a href='" + BaseTest.srcFileName + "' target='_blank'>Screenshot link</a>");
        Reporter.log("<br>");
        Reporter.log("<a href='" + BaseTest.srcFileName + "' target='_blank'><img src='" + BaseTest.srcFileName + "' height=200 width=200 /></a>");

        // Stack trace
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        test.fail("<details><summary><b><font color=red>Exception Occurred: Click to expand</font></b></summary>"
                + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

        // Extent Report log
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        test.fail(m);

        try {
            test.fail("<b><font color=red>Screenshot of failure</font></b><br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        test.skip(m);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
