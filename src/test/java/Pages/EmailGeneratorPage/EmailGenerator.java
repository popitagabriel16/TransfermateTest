package Pages.EmailGeneratorPage;

import TestBase.ExtentBaseMain;
import TestBase.PropertiesFile;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static TestBase.PropertiesFile.prop;

public class EmailGenerator {

    private WebDriver driver;
    private ExtentTest extentStep;

    @FindBy(id = "email_ch_text")
    WebElement emailText;
    @FindBy(xpath = "//*[contains(text(), 'Activate My Account')]")
    WebElement activateAccountButton;

    public EmailGenerator(WebDriver driver) {
        this.driver = driver;
    }

    public void extentReport(ExtentTest extentStep) {
        this.extentStep = extentStep;
    }

    /**
     * Generate an email and store it into config.properties
     *
     * @throws IOException
     */
    public boolean randomEmailGenerator() throws IOException {
        try {
            extentStep.info("emailRegen() has been called");
            String emailField = emailText.getText();
            PropertiesFile.setProperties(prop.setProperty("newGeneratedEmail", emailField));
            extentStep.info("Asserting that you are on the email regen page");
            extentStep.pass("emailRegen() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("emailRegen() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Activate account from Transfermate website
     *
     * @throws IOException
     */
    public boolean activateAccount() throws IOException {
        try {
            extentStep.info("activateAccount() has been called");
            Thread.sleep(8000);
            driver.get(PropertiesFile.prop.getProperty("mailGenerator") + PropertiesFile.prop.getProperty("newGeneratedEmail"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", activateAccountButton);
            extentStep.info("Scrolling down the page");
            activateAccountButton.click();
            extentStep.info("Clicking on activation mail button");
            extentStep.pass("activateAccount() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("activateAccount() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

}
