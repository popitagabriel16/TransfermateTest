package Tests;

import Pages.EmailGeneratorPage.EmailGenerator;
import Pages.RegistrationPage.Registration;
import TestBase.PropertiesFile;
import TestBase.TestSetup;
import com.aventstack.extentreports.ExtentTest;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static TestBase.ExtentBaseMain.extent;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransfermateTests extends TestSetup {

    private static Registration registration;
    private static EmailGenerator emailGenerator;

    @Before
    public void init() {
        registration = new Registration(driver);
        PageFactory.initElements(driver, registration);
        emailGenerator = new EmailGenerator(driver);
        PageFactory.initElements(driver, emailGenerator);
    }

    @Ignore
    @Test
    public void generateEmailTest() throws IOException {
        ExtentTest test = extent.createTest("generateEmail() test has been called").assignDevice(System.getProperty("os.name")).assignCategory("TransfermateTests");
        emailGenerator.extentReport(test);
        driver.get(PropertiesFile.prop.getProperty("mailGenerator"));
        Assert.assertTrue("Generating email is not successful", emailGenerator.randomEmailGenerator());
    }

    @Ignore
    @Test
    public void positiveRegistrationTest() throws IOException {
        ExtentTest test = extent.createTest("positiveRegistrationTest() test has been called").assignDevice(System.getProperty("os.name")).assignCategory("TransfermateTests");
        registration.extentReport(test);
        driver.get(PropertiesFile.prop.getProperty("registrationPage"));
        Assert.assertTrue("Entering account type is not successful", registration.enterAccountType(true));
        Assert.assertTrue("Selecting country type is not successful", registration.selectCountry(true));
        Assert.assertTrue("Entering name is not successful", registration.enterFirstName(true));
        Assert.assertTrue("Entering last name is not successful", registration.enterLastName(true));
        Assert.assertTrue("Entering new generated email", registration.enterEmail(true));
        Assert.assertTrue("Entering selecting country code and entering phone number is not successful", registration.selectPhoneCountry(true));
        Assert.assertTrue("Checking agreement radio button is not successful", registration.checkTermsAndConditions(true));
        Assert.assertTrue("Solving captcha math problem is not successful", registration.captchaCalculator(true));
        Assert.assertTrue("Validate page after clicking on registration is not successful", registration.createAccount());
    }

    @Ignore
    @Test
    public void verifyActivationMailTest() throws IOException {
        ExtentTest test = extent.createTest("mailActivationTest() test has been called").assignDevice(System.getProperty("os.name")).assignCategory("TransfermateTests");
        emailGenerator.extentReport(test);
        registration.extentReport(test);
        Assert.assertTrue("Activate account from mail is not successful", emailGenerator.activateAccount());
        Assert.assertTrue("Type password is not successful", registration.enterPassword());
        Assert.assertTrue("Verify phone sms verification is not successful", registration.phoneSMS());
    }

    @Ignore
    @Test
    public void negativeRegistrationTest() throws IOException {
        ExtentTest test = extent.createTest("positiveRegistrationTest() test has been called").assignDevice(System.getProperty("os.name")).assignCategory("TransfermateTests");
        registration.extentReport(test);
        driver.get(PropertiesFile.prop.getProperty("registrationPage"));
        Assert.assertTrue("Entering account type is not successful", registration.enterAccountType(false));
        Assert.assertTrue("Selecting country type is not successful", registration.selectCountry(false));
        Assert.assertTrue("Entering name is not successful", registration.enterFirstName(false));
        Assert.assertTrue("Entering last name is not successful", registration.enterLastName(true));
        Assert.assertTrue("Entering new generated email", registration.enterEmail(false));
        Assert.assertTrue("Entering selecting country code and entering phone number is not successful", registration.selectPhoneCountry(false));
        Assert.assertTrue("Checking agreement radio button is not successful", registration.checkTermsAndConditions(false));
        Assert.assertTrue("Solving captcha math problem is not successful", registration.captchaCalculator(false));
    }

}
