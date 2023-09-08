package FeatureSteps;

import Pages.EmailGeneratorPage.EmailGenerator;
import Pages.RegistrationPage.Registration;
import TestBase.PropertiesFile;
import TestBase.TestSetup;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static TestBase.ExtentBaseMain.extent;

public class StepsRegistration extends TestSetup {

    private static Registration registration;
    private static EmailGenerator emailGenerator;

    public static void init() throws IOException {
        registration = new Registration(driver);
        PageFactory.initElements(driver, registration);
        emailGenerator = new EmailGenerator(driver);
        PageFactory.initElements(driver, emailGenerator);
        ExtentTest test = extent.createTest("Cucumber test run");
        registration.extentReport(test);
        emailGenerator.extentReport(test);
    }

    @Given("browser is open")
    public void browser_is_open() throws Exception {
        beforeSetup();
    }
    @When("user navigates to URL")
    public void user_navigates_to_url() throws IOException {
        init();
        driver.get(PropertiesFile.prop.getProperty("mailGenerator"));
    }
    @Then("user fetch generated email")
    public void user_fetch_generated_email() throws IOException {
        init();
        Assert.assertTrue("Generating email is not successful", emailGenerator.randomEmailGenerator());
    }

    @Given("browser is opening to registration")
    public void browser_is_opening_to_registration() throws Exception {
        beforeSetup();
    }
    @When("user is navigating to Transfermate registration website")
    public void user_is_navigating_to_transfermate_registration_website() throws IOException {
        init();
        driver.get(PropertiesFile.prop.getProperty("registrationPage"));
    }
    @And("don't select any of the account type options")
    public void don_t_select_any_of_the_account_type_options() throws IOException {
        init();
        Assert.assertTrue("Entering account type is not successful", registration.enterAccountType(false));

    }
    @And("select wrong country account type")
    public void select_wrong_country_account_type() throws IOException {
        init();
        Assert.assertTrue("Selecting country type is not successful", registration.selectCountry(false));
    }
    @And("type numbers in First Name field")
    public void type_numbers_in_first_name_field() throws IOException {
        init();
        Assert.assertTrue("Entering name is not successful", registration.enterFirstName(false));
    }
    @And("type numbers in Last Name field")
    public void type_numbers_in_last_name_field() throws IOException {
        init();
        Assert.assertTrue("Entering last name is not successful", registration.enterLastName(true));
    }
    @And("leave email address empty")
    public void leave_email_address_empty() throws IOException {
        init();
        Assert.assertTrue("Entering new generated email", registration.enterEmail(false));
    }
    @And("select country code and type letters in mobile phone number field")
    public void select_country_code_and_type_letters_in_mobile_phone_number_field() throws IOException {
        init();
        Assert.assertTrue("Entering selecting country code and entering phone number is not successful", registration.selectPhoneCountry(false));
    }
    @And("don't check agreement policy")
    public void don_t_check_agreement_policy() throws IOException {
        init();
        Assert.assertTrue("Checking agreement radio button is not successful", registration.checkTermsAndConditions(false));
    }
    @Then("type wrong number to resolve captcha math problem")
    public void type_wrong_number_to_resolve_captcha_math_problem() throws IOException {
        init();
        Assert.assertTrue("Solving captcha math problem is not successful", registration.captchaCalculator(false));
    }

        @Given("browser is opening")
        public void browser_is_opening() throws Exception {
            beforeSetup();
        }
        @When("user is navigating to Transfermate registration page")
        public void user_is_navigating_to_transfermate_registration_page() {
            driver.get(PropertiesFile.prop.getProperty("registrationPage"));
        }
        @And("select any of the account type options")
        public void select_any_of_the_account_type_options() throws IOException {
            init();
            Assert.assertTrue("Entering account type is not successful", registration.enterAccountType(true));
        }
        @When("select a country account type")
        public void select_a_country_account_type() throws IOException {
            init();
            Assert.assertTrue("Selecting country type is not successful", registration.selectCountry(true));
        }
        @And("type a proper First Name in the field")
        public void type_a_proper_first_name_in_the_field() throws IOException {
            init();
            Assert.assertTrue("Entering name is not successful", registration.enterFirstName(true));
        }
        @And("type a proper Last Name in the field")
        public void type_a_proper_last_name_in_the_field() throws IOException {
            init();
            Assert.assertTrue("Entering last name is not successful", registration.enterLastName(true));
        }
        @And("type a proper Email address in the field")
        public void type_a_proper_email_address_in_the_field() throws IOException {
            init();
            Assert.assertTrue("Entering new generated email", registration.enterEmail(true));
        }
        @And("select any country code and type mobile phone number")
        public void select_any_country_code_and_type_mobile_phone_number() throws IOException {
            init();
            Assert.assertTrue("Entering selecting country code and entering phone number is not successful", registration.selectPhoneCountry(true));
        }
        @And("check agreement policy")
        public void check_agreement_policy() throws IOException {
            init();
            Assert.assertTrue("Checking agreement radio button is not successful", registration.checkTermsAndConditions(true));
        }
        @And("resolve captcha math problem")
        public void resolve_captcha_math_problem() throws IOException {
            init();
            Assert.assertTrue("Solving captcha math problem is not successful", registration.captchaCalculator(true));
        }
        @Then("account creates successfully")
        public void account_creates_successfully() throws IOException {
            init();
            Assert.assertTrue("Validate page after clicking on registration is not successful", registration.createAccount());
        }

//    @Given("browser chrome is open on Email website")
//    public void browser_chrome_is_open_on_email_website() throws Exception {
//        beforeSetup();
//    }
    @When("type the email that has been generated")
    public void type_the_email_that_has_been_generated() throws IOException {
        init();
        Assert.assertTrue("Activate account from mail is not successful", emailGenerator.activateAccount());
    }
    @And("type the password to fill mandatory information")
    public void type_the_password_to_fill_mandatory_information() throws IOException {
        init();
        Assert.assertTrue("Type password is not successful", registration.enterPassword());
    }
    @Then("activate by API the phone number sms generated")
    public void activate_by_api_the_phone_number_sms_generated() throws IOException {
        init();
        Assert.assertTrue("Verify phone sms verification is not successful", registration.phoneSMS());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failingScreenshot");
        }
        driver.quit();
    }

}
