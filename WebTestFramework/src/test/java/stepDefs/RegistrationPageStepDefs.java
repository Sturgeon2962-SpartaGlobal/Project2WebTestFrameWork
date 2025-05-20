package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import utils.TestContextSetup;

import java.io.IOException;
import java.util.regex.Matcher;

public class RegistrationPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;

    public RegistrationPageStepDefs(TestContextSetup testContextSetup){
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
    }

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() throws IOException, InterruptedException {
        Thread.sleep(500);
        landingPage.selectLoginBtn();
        Thread.sleep(500);
        loginPage.selectRegisterBtn();
        Thread.sleep(500);
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        String expectedURL = "https://bookcart.azurewebsites.net/register";
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

    @When("the user enters {string}, {string}, {string}, {string}, {string} and {string}")
    public void the_user_enters(String firstName, String lastName, String userName, String password1, String password2, String gender) throws InterruptedException {
        registrationPage.CompleteForm(
                firstName,
                lastName,
                userName,
                password1,
                password2,
                gender
        );
//        Thread.sleep(1000);
    }

    @When("the user clicks the Register button")
    public void the_user_clicks_the_register_button() {
        registrationPage.SubmitForm();
    }

    @Then("the user should be redirected to the welcome page")
    public void the_user_should_be_redirected_to_the_welcome_page() throws IOException, InterruptedException {
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        String expectedURL = "https://bookcart.azurewebsites.net/register";
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

    @Then("the user should see an username all ready taken")
    public void theUserShouldSeeAnUserNameNotAvailableError() {
        String errorMsg = registrationPage.GetUserNameErrorMsg();
        MatcherAssert.assertThat(errorMsg, Matchers.containsString("User Name is not available"));
    }

    @Then("the error message {string} Should be displyed")
    public void theErrorMessageShouldBeDisplyed(String errorMessage) {
        String webErrorMsg = registrationPage.GetWeakPasswordErrorMsg();
        MatcherAssert.assertThat(webErrorMsg, Matchers.containsString(errorMessage));
    }

}
