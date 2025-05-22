package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.TestContextSetup;

import java.io.IOException;
import java.time.Duration;

public class CheckoutPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;
    BookPage bookPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;

    public CheckoutPageStepDefs(TestContextSetup testContextSetup) throws IOException, InterruptedException {
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.bookPage = testContextSetup.pageObjectManager.getBookPage();
        this.cartPage = testContextSetup.pageObjectManager.getCartPage();
        this.checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
        this.wait =  new WebDriverWait(testContextSetup.testBase.WebDriverManager(), Duration.ofSeconds(20));
    }



    @Given("I am on the checkout page")
    public void iAmOnTheCheckoutPage() throws InterruptedException {
        Thread.sleep(500);
        landingPage.clickOnCartIcon();
        Thread.sleep(500);
        cartPage.clickCheckoutBtn();
        Thread.sleep(500);
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/checkout"));
    }

    @When("I enter valid payment information")
    public void iEnterValidPaymentInformation() {
        checkoutPage.completeShippingForm();
    }

    @And("I confirm the purchase")
    public void iConfirmThePurchase() throws InterruptedException {
        checkoutPage.submitShippingForm();
    }

    @Then("I should see an order confirmation message")
    public void iShouldSeeAnOrderConfirmationMessage() throws IOException, InterruptedException {
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/myorders"));
        String expectedUrl = "https://bookcart.azurewebsites.net/myorders";
        String actualUrl = testContextSetup.testBase.WebDriverManager().getCurrentUrl();;
        MatcherAssert.assertThat(expectedUrl, Matchers.is(actualUrl));
    }

    @When("I leave payment fields empty")
    public void iLeavePaymentFieldsEmpty() {

    }

    @When("I submit the form")
    public void iSubmitTheForm() throws InterruptedException {
        checkoutPage.submitShippingForm();
    }

    @Then("I should see an error indicating required fields")
    public void iShouldSeeAnErrorIndicatingRequiredFields() {
        boolean error =  checkoutPage.getErrorMsgs();
        MatcherAssert.assertThat(error, Matchers.is(true));
    }
}
