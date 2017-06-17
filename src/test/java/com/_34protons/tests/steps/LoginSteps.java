package com._34protons.tests.steps;

import com._34protons.pages.LoginPage;
import com._34protons.pages.MainPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private final LoginPage loginPage;
    private final MainPage mainPage;

    public LoginSteps(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    /*
     * Non capturing group to allow alternation between alternatives
     * 'visit' and 'go to' without creating a parameter
    */
    @Given("^I (?:visit|go to) the login page$")
    public void i_go_to_the_page() throws Throwable {
        loginPage.open();
    };

    /*
     * Note that prefix 'in' is optional so this will match both invalid and valid
     */
    @When("^I enter (?:invalid|valid) the credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_enter_valid_the_credentials_and(String username, String password) throws Throwable {
        loginPage.executeLogin(username, password);
    }

    @Then("^I (should|should not) be able to access the landing page$")
    public void iShouldNotBeAbleToAccessTheLandingPage(String intention) throws Throwable {
        String expected = "Demo page for selenium code";
        if(intention.contains("not")) {
            expected = "Username or password invalid";
            assertEquals(expected, loginPage.getErrMsg());
            return;
        } else {
            assertEquals(expected, mainPage.getPageTitle());
        }
    }
}
