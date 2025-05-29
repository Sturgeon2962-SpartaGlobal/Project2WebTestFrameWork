package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.TestContextSetup;

import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;

public class LoginPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;
    BookPage bookPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;

    public LoginPageStepDefs(TestContextSetup testContextSetup) throws IOException, InterruptedException {
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.bookPage = testContextSetup.pageObjectManager.getBookPage();
        this.cartPage = testContextSetup.pageObjectManager.getCartPage();
        this.checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
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
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/"));
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        String expectedURL = "https://bookcart.azurewebsites.net/";
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

    @Given("I am logged in")
    public void iAmLoggedIn() throws InterruptedException {
        landingPage.selectLoginBtn();
        Thread.sleep(500);
        loginPage.CompleteLoginForm("SoftwareTest1", "ValidPa55word!");
        loginPage.SubmitLoginForm();
    }

    @When("I enter incorrect credentials")
    public void iEnterIncorrectCredentials() {
        loginPage.CompleteLoginForm("ThisIsInvalid", "ValidPa55word!");

    }

    @Then("I should stay on the login page")
    public void iShouldStayOnTheLoginPage() throws IOException {
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/login"));
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();
        String expectedURL = "https://bookcart.azurewebsites.net/login";
        MatcherAssert.assertThat(actualURL, Matchers.is(expectedURL));
    }

    @When("I enter username but no password")
    public void iEnterUsernameButNoPassword() {
        loginPage.CompleteLoginForm("SoftwareTest1", "");
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() throws InterruptedException {
        Thread.sleep(250);
        Boolean errorMsg = loginPage.getErrorMsg();
        MatcherAssert.assertThat(errorMsg, Matchers.is(Boolean.TRUE));
    }
}
