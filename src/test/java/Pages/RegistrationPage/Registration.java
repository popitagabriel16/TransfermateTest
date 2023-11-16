package Pages.RegistrationPage;

import Pages.ElementsSeparateFromMethods.Methods;
import Pages.EmailGeneratorPage.EmailGenerator;
import TestBase.ExtentBaseMain;
import TestBase.PropertiesFile;
import com.aventstack.extentreports.ExtentTest;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Registration {

    private WebDriver driver;
    private ExtentTest extentStep;
    Random random = new Random();
    private EmailGenerator eGen;
    private Methods methods;
    public static final String ACCOUNT_SID = "ACfe44d277461293b4d966c8f974e0035b";
    public static final String AUTH_TOKEN = "e809d0ec8b7cfb747881f6499c887756";

    @FindBy(id = "user_pin")
    WebElement pinField;
    @FindBy(id = "popup-verify-pin-btn")
    WebElement pinVerificationButton;
    @FindBy(id = "menu_481_32_28607")
    WebElement bottomPage;
    @FindBy(id = "cookies-read-more-link")
    WebElement cookiesAccept;
    @FindBy(id = "button_subscribe")
    WebElement createAccountButton;
    @FindBy(id = "country")
    WebElement countrySelector;
    @FindBy(id = "first_name")
    WebElement firstName;
    @FindBy(id = "last_name")
    WebElement lastName;
    @FindBy(id = "__pin_mobile_number_international_dialing_code")
    WebElement phoneNumberSelector;
    @FindBy(id = "email")
    WebElement emailField;
    @FindBy(id = "register_terms_of_use_agree_form_input")
    WebElement checkTerms;
    @FindBy(id = "__calc_captcha_text")
    WebElement captchaField;
    @FindBy(id = "cal_captcha_f10_question")
    WebElement captchaProblemText;
    @FindBy(id = "__pin_mobile_number_mobile_phone")
    WebElement phoneField;
    @FindBy(xpath = "//*[contains(text(), 'Check your mail')]")
    WebElement checkMailMessage;
    @FindBy(xpath = "//*[@class='check-radio-label']")
    List<WebElement> randomTypeAccount;
    @FindBy(xpath = "//div[contains(text(), 'Please enter correct information!')]")
    WebElement captchaError;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "confirm_password")
    WebElement confirmPasswordField;
    @FindBy(id = "button_subscribe")
    WebElement submitButton;
    @FindBy(xpath = "//*[contains(text(), 'Select your country')]")
    WebElement selectCountry;
    @FindBy(xpath = "//*[contains(text(), 'USA +1')]")
    WebElement selectUSA;
    @FindBy(xpath = "(//*[contains(text(), 'Bulgaria')])[2]")
    WebElement selectBulgariaCountry;
    By successActivation = By.xpath("//*[contains(text(), 'Preparing your account')][1]");
    By countryError = By.xpath("//*[@id='register_country_error' and contains(text(),'Please enter correct information!')]");
    By educationTypeError = By.xpath("//*[@id='register_account_type_education_error' and contains(text(),'Please Select Account Type')]");
    By individualTypeError = By.xpath("//*[@id='register_account_type_individual_error' and contains(text(),'Please Select Account Type')]");
    By corporateTypeError = By.xpath("//*[@id='register_account_type_corporate_error' and contains(text(),'Please Select Account Type')]");
    By partnerTypeError = By.xpath("//*[@id='register_account_type_partnership_error' and contains(text(),'Please Select Account Type')]");
    By soleTraderTypeError = By.xpath("//*[@id='register_account_type_sole_trader_error' and contains(text(),'Please Select Account Type')]");
    By firstNameError = By.xpath("//*[@id='register_first_name_error' and contains(text(),'Please enter correct information!')]");
    By lastNameError = By.xpath("//*[@id='register_last_name_error' and contains(text(),'Please enter correct information!')]");
    By emailError = By.xpath("//*[@id='register_email_error' and contains(text(),'The field is mandatory')]");
    By phoneError = By.xpath("//*[@id='register___pin_mobile_number_mobile_phone_error' and contains(text(),'Please enter correct information!')]");
    By agreementError = By.xpath("//*[@id='register_terms_of_use_agree_error' and contains(text(),'Click OK to return and check the box that you have read and agree with our Terms of Use')]");
    By captchaErrorMessage = By.xpath("//*[@id='register___calc_captcha_text_error' and contains(text(),'Please enter correct information!')]");


    public Registration(WebDriver driver) {
        this.driver = driver;
        this.eGen = new EmailGenerator(driver);
        this.methods = new Methods(driver);
    }

    public void extentReport(ExtentTest extentStep) {
        this.extentStep = extentStep;
    }

    /**
     * Selects a random account type or leave account type empty to generate an error and verify it
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean enterAccountType(boolean changedSteps) throws IOException {
        try {
            extentStep.info("enterAccountType() has been called");
            methods.acceptElementCookie();
            //cookiesAccept.click();
            extentStep.info("Accepting cookies");
            if (changedSteps) {
                int start = 0;
                int end = 4;

                int index = start + random.nextInt(end - start + 1);
                randomTypeAccount.get(index).click();
            } else {
                createAccountButton.click();
                extentStep.info("Clicking on create account button");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(educationTypeError));
                wait.until(ExpectedConditions.presenceOfElementLocated(individualTypeError));
                wait.until(ExpectedConditions.presenceOfElementLocated(corporateTypeError));
                wait.until(ExpectedConditions.presenceOfElementLocated(partnerTypeError));
                wait.until(ExpectedConditions.presenceOfElementLocated(soleTraderTypeError));
            }
            extentStep.info("Selecting account type");
            extentStep.pass("enterAccountType() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("enterAccountType() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Selects Bulgaria as a country account type or selects contry as "Select Country" to error generated and verify it
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean selectCountry(boolean changedSteps) throws IOException {
        try {
            extentStep.info("selectCountry() has been called");
            if (changedSteps) {
                countrySelector.click();
                extentStep.info("Clicking on selecting country");
                selectBulgariaCountry.click();
                //Select random country but I have to select a country that won't retrive an error message
//                Select countrySelect = new Select(countrySelector);
//                int index = random.nextInt(countrySelect.getOptions().size());
//                countrySelect.selectByIndex(index);
            } else {
                countrySelector.click();
                extentStep.info("Clicking on country selector");
                selectCountry.click();
                extentStep.info("Select country that produce error");
                createAccountButton.click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(countryError));
            }

            extentStep.info("Selecting country");
            extentStep.pass("selectCountry() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("selectCountry() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Types First Name by random String or types random string numbers and verify error message
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean enterFirstName(boolean changedSteps) throws IOException {
        try {
            extentStep.info("enterFirstName() has been called");
            String randomName = RandomStringUtils.randomAlphabetic(2, 20);
            String randomNumbers = RandomStringUtils.randomNumeric(20, 50);
            if (changedSteps) {
                firstName.sendKeys(randomName);
            } else {
                firstName.sendKeys(randomNumbers);
                extentStep.info("Texting in First Name field: " + randomNumbers);
                createAccountButton.click();
                extentStep.info("Clicking on create account");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(firstNameError));
            }
            extentStep.info("Typing random Alphabetical letters = " + randomName);
            extentStep.pass("enterFirstName() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("enterFirstName() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Types Last Name by random String or types random string numbers and verify error message
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean enterLastName(boolean changedSteps) throws IOException {
        try {
            extentStep.info("enterLastName() has been called");
            String randomLastName = RandomStringUtils.randomAlphabetic(2, 20);
            String randomNumbers = RandomStringUtils.randomNumeric(20, 50);
            if (changedSteps) {
                lastName.sendKeys(randomLastName);
                extentStep.info("Texting in the last name field: " + randomLastName);
            } else {
                lastName.sendKeys(randomNumbers);
                extentStep.info("Texting in the last name field: " + randomNumbers);
                createAccountButton.click();
                extentStep.info("Clicking on create account");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(lastNameError));
            }
            extentStep.info("Typing random Alphabetical letters = " + randomLastName);
            extentStep.pass("enterLastName() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("enterLastName() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Types email that has been generated by @EmailGenerator.class or leave field empty and verify error message
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean enterEmail(boolean changedSteps) throws IOException {
        try {
            extentStep.info("enterEmail() has been called");
            if (changedSteps) {
                emailField.sendKeys(PropertiesFile.prop.getProperty("newGeneratedEmail"));
                extentStep.info("Generating email: " + PropertiesFile.prop.getProperty("newGeneratedEmail"));
            } else {
                createAccountButton.click();
                extentStep.info("Clicking on create account");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(emailError));
            }
            extentStep.pass("enterEmail() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("enterEmail() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Selects country code USA and sends phone number for API LOG or sends keys to verify error message
     *
     * @param changedSteps changes the steps of UI
     * @throws IOException
     */
    public boolean selectPhoneCountry(boolean changedSteps) throws IOException {
        try {
            extentStep.info("selectPhoneCountry() has been called");
            phoneNumberSelector.click();
            extentStep.info("Clicking on selecting country");

            selectUSA.click();
            extentStep.info("Selecting phone number contry code");

            //To randomize the phone number country code
//            Select countrySelect = new Select(phoneNumberSelector);
//            int index = random.nextInt(countrySelect.getOptions().size());
//            countrySelect.selectByIndex(index);

            if (changedSteps) {
                String randomNumbers = RandomStringUtils.randomNumeric(8, 11);
                phoneField.sendKeys(PropertiesFile.prop.getProperty("phoneNumberUSA"));
                extentStep.info("Typing random Alphabetical letters = " + randomNumbers);
            } else {
                String randomLetters = RandomStringUtils.randomAlphabetic(8, 11);
                phoneField.sendKeys(randomLetters);
                extentStep.info("Texting in phone field letters: " + randomLetters);
                createAccountButton.click();
                extentStep.info("Clicking on create account");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(phoneError));
            }

            extentStep.pass("selectPhoneCountry() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("selectPhoneCountry() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Check the terms and conditions or leaves it unchecked to verify error if displayed
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean checkTermsAndConditions(boolean changedSteps) throws IOException {
        try {
            extentStep.info("checkTermsAndConditions() has been called");
            if (changedSteps) {
                checkTerms.click();
                extentStep.info("Clicking on Terms and Condition agree checkbox");
            } else {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(agreementError));
            }
            extentStep.pass("checkTermsAndConditions() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("checkTermsAndConditions() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Calculates captcha problem (THIS METHOD IS UNSTABLE AND SOMETIMES IT FAILS BECAUSE OF JAVASCRIPT TOEKN), or generate random number to verify error displayed
     *
     * @param changedSteps
     * @throws IOException
     */
    public boolean captchaCalculator(boolean changedSteps) throws IOException {
        try {
            extentStep.info("captchaCalculator() has been called");
            String randomNumbers = RandomStringUtils.randomNumeric(20, 50);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", bottomPage);
            extentStep.info("Scrolling to the middle page");
            Thread.sleep(4000);
            String captchaMathProblem = captchaProblemText.getText().replace("=", "").replace("\n", "");

            Expression calc = new ExpressionBuilder(captchaMathProblem).build();
            double result1 = calc.evaluate();
            String finalResults = String.valueOf(((int) result1));

            Thread.sleep(5000);
            if (changedSteps) {
                while (captchaField.getAttribute("value").isEmpty()) {
                    Thread.sleep(1000);
                    captchaField.sendKeys(finalResults);
                }
            } else {
                while (captchaField.getAttribute("value").isEmpty()) {
                    Thread.sleep(1000);
                    captchaField.sendKeys(randomNumbers);
                    createAccountButton.click();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.presenceOfElementLocated(captchaErrorMessage));
                }
            }
            extentStep.info("Solving captcha calculator problem");
            extentStep.pass("captchaCalculator() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("captchaCalculator() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }

    }

    /**
     * Clicks on create account to make registration successful
     *
     * @throws IOException
     */
    public boolean createAccount() throws IOException {
        try {
            extentStep.info("createAccount() has been called");
            createAccountButton.click();
            extentStep.info("Clicking on create account button");
            if (checkMailMessage.isDisplayed()) {
                extentStep.info("Validate sign-up page");
                extentStep.pass("createAccount() has passed");
                return true;
            }
            return true;
        } catch (Exception ex) {
            extentStep.fail("createAccount() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Generate password towards to activate the account by email
     *
     * @param length
     * @return
     */
    public static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rng = new Random();
        char[] password = new char[length];
        int index = 0;

        password[index++] = characters.charAt(rng.nextInt(26)); // Add an uppercase letter
        password[index++] = characters.charAt(26 + rng.nextInt(26)); // Add a lowercase letter
        password[index++] = characters.charAt(52 + rng.nextInt(10)); // Add a number

        while (index < length) {
            password[index] = characters.charAt(rng.nextInt(characters.length()));
            index++;
        }

        return new String(password);
    }

    /**
     * Enter random password that has been generated by the website conditions
     *
     * @throws IOException
     */
    public boolean enterPassword() throws IOException {
        try {
            extentStep.info("enterPassword() has been called");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            extentStep.info("Switching tab");

            String passwordGenerated = generatePassword(8);
            extentStep.info("Generating password");
            passwordField.sendKeys(passwordGenerated);
            extentStep.info("Sending keys to password field: " + passwordGenerated);
            confirmPasswordField.sendKeys(passwordGenerated);
            extentStep.info("Sending keys to confirm password field: " + passwordGenerated);
            submitButton.click();
            extentStep.info("Clicking on submit button");
            extentStep.pass("enterPassword() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("enterPassword() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Type the SMS from phone to verify account
     *
     * @throws IOException
     */
    public boolean phoneSMS() throws IOException {
        try {
            extentStep.info("phoneSMS() has been called");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            String verificationNumber = getMessage().substring(25, 31);
            pinField.sendKeys(verificationNumber);
            pinVerificationButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(successActivation));
            extentStep.pass("phoneSMS() has passed");
            return true;
        } catch (Exception ex) {
            extentStep.fail("phoneSMS() has failed");
            extentStep.addScreenCaptureFromPath(ExtentBaseMain.capture(driver));
            return false;
        }
    }

    /**
     * Get SMS CODE
     */
    public static String getMessage() {
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+17472988674")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Get SMS CODE
     */
    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }

}
