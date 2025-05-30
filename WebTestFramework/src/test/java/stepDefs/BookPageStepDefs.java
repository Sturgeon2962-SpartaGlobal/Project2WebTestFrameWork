package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.TestContextSetup;

import java.io.IOException;
import java.time.Duration;

public class BookPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;
    BookPage bookPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;

    public BookPageStepDefs(TestContextSetup testContextSetup) throws IOException, InterruptedException {
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.bookPage = testContextSetup.pageObjectManager.getBookPage();
        this.cartPage = testContextSetup.pageObjectManager.getCartPage();
        this.checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
        this.wait =  new WebDriverWait(testContextSetup.testBase.WebDriverManager(), Duration.ofSeconds(20));
    }

    @Given("I am viewing a book's detail page")
    public void iAmViewingABooksDetailPage() throws IOException, InterruptedException {
        landingPage.clickOnBookCard();
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/books/details/2"));
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();;
        String ExpectedURL = "https://bookcart.azurewebsites.net/books/details/2";
        MatcherAssert.assertThat(actualURL, Matchers.is(ExpectedURL));
    }

    @When("I click on Add to Cart button")
    public void iClickOnAddToCartButton() throws InterruptedException {
        System.out.println("Click on Add to Cart button");
        bookPage.AddToCart();
        Thread.sleep(500);
    }

    @Then("the item should appear in my cart with quantity {string}")
    public void theItemShouldAppearInMyCartWithQuantity1(String numOfBooks) throws InterruptedException {
        String cartCount = bookPage.getCartCount();
        MatcherAssert.assertThat(cartCount, Matchers.equalTo(numOfBooks));
    }

    @Given("I have already added a book to the cart")
    public void iHaveAlreadyAddedABookToTheCart() throws InterruptedException {
        System.out.println("I have already added a book to the cart");
        landingPage.clickOnBookCard();
        bookPage.AddToCart();
        Thread.sleep(500);
        String cartCount = bookPage.getCartCount();
        MatcherAssert.assertThat(cartCount, Matchers.is("1"));
    }

    @Then("the quantity of that book in the cart should increase to {string}")
    public void theQuantityOfThatBookInTheCartShouldIncreaseBy(String numOfBooks) throws InterruptedException {
        String cartCount = bookPage.getCartCount();
        System.out.println("Cart count is: " + cartCount);
        MatcherAssert.assertThat(cartCount, Matchers.is(numOfBooks));
    }
}
