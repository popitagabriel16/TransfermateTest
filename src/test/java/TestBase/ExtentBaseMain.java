package TestBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentBaseMain {

    public static ExtentSparkReporter spark;
    public static ExtentReports extent;

    public static void createExtent() {
        if (extent == null) {
            spark = new ExtentSparkReporter("target/reports/report.html");
            extent = new ExtentReports();
            spark.config().setDocumentTitle("Regression Testing");
            extent.attachReporter(spark);
        }
    }

    public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("target/reports/failedTestsScreenshots/" + System.currentTimeMillis()
                + ".png");
        String picturePath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return picturePath;
    }
}
