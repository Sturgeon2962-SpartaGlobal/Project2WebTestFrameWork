package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import utils.TestContextSetup;

import java.io.IOException;
import java.time.Duration;

public class LoginPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;
    WebDriverWait wait;

    public LoginPageStepDefs(TestContextSetup testContextSetup) throws IOException, InterruptedException {
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.wait =  new WebDriverWait(testContextSetup.testBase.WebDriverManager(), Duration.ofSeconds(20));
    }

    @Given("I have navigated to the login page")
    public void iHaveNavigatedToTheLogInPage() throws InterruptedException {
        landingPage.selectLoginBtn();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @When("I complete the log-in form with valid log-in credentials")
    public void iCompleteTheLogInFormWithValidLogInCredentials() throws InterruptedException, IOException {
        loginPage.CompleteLoginForm("SoftwareTest1", "ValidPa55word!");
    }

    @Then("I click the login button")
    public void iClickTheLoginButton() throws InterruptedException, IOException {
        loginPage.SubmitLoginForm();
    }

    @Then("I should be redirected to the homepage")
    public void iShouldBeRedirectedToTheHomepage() throws InterruptedException, IOException {
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        String expectedURL = "https://bookcart.azurewebsites.net";
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

}
