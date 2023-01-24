Feature: Registration

  @generateEmail
  Scenario: Generate an email
    Given browser is open
    When user navigates to URL
    Then user fetch generated email

  @positiveRegistration
  Scenario: Verify successful registration
      Given browser is opening
      When user is navigating to Transfermate registration page
      And select any of the account type options
      And select a country account type
      And type a proper First Name in the field
      And type a proper Last Name in the field
      And type a proper Email address in the field
      And select any country code and type mobile phone number
      And check agreement policy
      And resolve captcha math problem
      Then account creates successfully

  @negativeRegistration
  Scenario:Verify unsuccessful registration
    Given browser is opening to registration
    When user is navigating to Transfermate registration website
    And don't select any of the account type options
    And select wrong country account type
    And type numbers in First Name field
    And type numbers in Last Name field
    And leave email address empty
    And select country code and type letters in mobile phone number field
    And don't check agreement policy
    Then type wrong number to resolve captcha math problem


  @verifyActivationMail
  Scenario: Verify account by phone number SMS
    Given browser chrome is open on Email website
    When type the email that has been generated
    And type the password to fill mandatory information
    Then activate by API the phone number sms generated

