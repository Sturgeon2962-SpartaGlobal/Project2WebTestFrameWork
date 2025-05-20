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
import java.util.List;

public class LandingPageStepDefs {
    TestContextSetup testContextSetup;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LandingPage landingPage;
    WebDriverWait wait;

    public LandingPageStepDefs(TestContextSetup testContextSetup) throws IOException, InterruptedException {
        this.testContextSetup = testContextSetup;
        this.registrationPage = testContextSetup.pageObjectManager.getRegistrationPage();
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.wait =  new WebDriverWait(testContextSetup.testBase.WebDriverManager(), Duration.ofSeconds(20));
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() throws IOException, InterruptedException {
        wait.until(ExpectedConditions.urlToBe("https://bookcart.azurewebsites.net/"));
        String actualURL = testContextSetup.testBase.WebDriverManager().getCurrentUrl();;
        String ExpectedURL = "https://bookcart.azurewebsites.net/";
        MatcherAssert.assertThat(actualURL, Matchers.is(ExpectedURL));
    }
    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String title) {
        landingPage.searchTitle(title);
    }
    @Then("I should see a list of books with {string} in the title or author")
    public void i_should_see_a_list_of_books_with_in_the_title_or_author(String title) {
        List<String> bookTitles = landingPage.getAllBooks();

        for (String bookTitle : bookTitles) {
            MatcherAssert.assertThat(bookTitle, Matchers.containsString(title.toLowerCase()));
        }
    }

    @Then("I should see a {string} message")
    public void iShouldSeeAMessage(String expectedErrorMsg) {
        String errorMsg = landingPage.getErrorMsg();
        MatcherAssert.assertThat(errorMsg, Matchers.containsString(expectedErrorMsg));
    }
}
