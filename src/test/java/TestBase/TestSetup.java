package TestBase;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static TestBase.ExtentBaseMain.extent;

public class TestSetup {
    public static WebDriver driver = null;
    public static String browserName = null;

    @Before
    public void beforeSetup() throws Exception {
        ExtentBaseMain.createExtent();
        PropertiesFile.getProperties();
        String path = System.getProperty("user.dir");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-file-access-from-files",
                "--use-fake-ui-for-media-stream",
                "--allow-file-access",
                "--use-file-for-fake-audio-capture=" + path + "/utils/video.mp4",
                "--use-fake-device-for-media-stream",
                "--disable-javascript");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", path + "/drivers/chromedriver/chromedriver.exe");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", path + "/drivers/geckodriver/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.gecko.driver", path + "/drivers/edgedriver/edgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void afterSetup() {
        extent.flush();
        driver.quit();
    }
}
