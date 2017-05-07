package com._34protons.tests.login;

import com._34protons.pages.MainPage;
import com._34protons.pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {

    boolean True = true;

    /*
     * Non capturing group to allow alternation between alternatives
     * 'visit' and 'go to' without creating a parameter
    */
    @Given("^I (?:visit|go to) the login page$")
    public void i_go_to_the_page() throws Throwable {
        assertTrue(True);
    };

    /*
     * Note that prefix 'in' is optional so this will match both invalid and valid
     */
    @When("^I enter (?:invalid|valid) the credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_enter_valid_the_credentials_and(String username, String password) throws Throwable {
        LoginPage login = new LoginPage();
        login.executeLogin(username, password);
    }

    @Then("^I (should|should not) be able to access the landing page$")
    public void iShouldNotBeAbleToAccessTheLandingPage(String intention) throws Throwable {
        String expected = "";
        if(!intention.contains("not")) {
            expected = "";
        }

        MainPage main = new MainPage();
        assertEquals(expected, main.getPageTitle());
    }
}
