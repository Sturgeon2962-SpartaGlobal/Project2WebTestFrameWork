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

public class LoginPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;

    public LoginPageStepDefs(TestContextSetup testContextSetup){
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
    }

    @Given("I am a registered user")
    public void i_am_a_registered_user(){

    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws IOException, InterruptedException {
        landingPage.selectLoginBtn();
        Thread.sleep(1000);
        String expectedURL = "https://bookcart.azurewebsites.net/login";
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials(){
        loginPage.CompleteLoginForm("username","ValidPa55word!");
    }

    @When("I click the login button")
    public void i_click_the_login_button(){
        loginPage.SubmitLoginForm();
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() throws IOException, InterruptedException {
        String expectedURL = "https://bookcart.azurewebsites.net/";

        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

}
