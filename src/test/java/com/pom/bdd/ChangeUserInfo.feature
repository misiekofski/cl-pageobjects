Feature: Change user information after login

  Scenario: Change user birthday date, gender and sign up for a newsletter
    Given User is logged in to CodersLab shop
    When He goes to UserInformationPage
    And He signs up for our newsletter
    And He saves information
    Then He sees "Information successfully updated."